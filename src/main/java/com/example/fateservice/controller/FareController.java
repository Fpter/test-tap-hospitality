package com.example.fateservice.controller;

import com.example.fateservice.dto.FareCalculationDTO;
import com.example.fateservice.entity.Driver;
import com.example.fateservice.entity.FareHistory;
import com.example.fateservice.exception.FormatFileException;
import com.example.fateservice.service.DriverService;
import com.example.fateservice.service.FareHistoryService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/fare")
public class FareController {

    @Autowired
    private FareHistoryService fareHistoryService;

    @Autowired
    private DriverService driverService;

    /**
     * Calculate the cheapest fare with registered drivers.
     *
     * @param csvFile MultipartFile
     * @return MultipartFile
     * @throws IOException
     * @throws CsvValidationException
     */
    @PostMapping("calculate")
    public ResponseEntity<FareHistory> calFare(@RequestParam("csvFile") MultipartFile csvFile) throws IOException, CsvValidationException {

        // Read data from file csv.
        Reader reader = new InputStreamReader(csvFile.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // bỏ qua header
        String[] line = csvReader.readNext();

        // Get fare information contain: Distance Traveled, Traveled Unit, and Cost Per Distance Traveled.
        FareCalculationDTO fare;
        try {
            fare = FareCalculationDTO.builder()
                    .distanceTraveled(Double.parseDouble(line[0]))
                    .traveledUnit(Double.parseDouble(line[1]))
                    .costPerDistanceTraveled(Double.parseDouble(line[2]))
                    .build();
        } catch (Exception ex) {
            throw new FormatFileException(csvFile.getName());
        }

        // Calculate the cheapest fare.
        Driver driver = driverService.findFareMin(fare);
        FareHistory fareHistory = FareHistory.builder()
                .driverId(driver.getId())
                .curBaseFarePrice(driver.getBaseFarePrice())
                .curBaseFareDistance(driver.getBaseFareDistance())
                .costPerDistanceTraveled(fare.getCostPerDistanceTraveled())
                .distanceTraveled(fare.getDistanceTraveled())
                .traveledUnit(fare.getTraveledUnit())
                .total(getTotalFare(driver, fare))
                .build();
        fareHistoryService.save(fareHistory);
        return ResponseEntity.ok(fareHistory);
    }

    // Get total fare with infomation of driver and fare
    private double getTotalFare(Driver driver, FareCalculationDTO fare) {
        return driver.getBaseFarePrice() +
                (fare.getDistanceTraveled() - driver.getBaseFareDistance())
                        / fare.getTraveledUnit()
                        * fare.getCostPerDistanceTraveled();

    }

    /**
     * Get history of calculation fare
     *
     * @return ResponseEntity<List<FareHistory>>
     */
    @GetMapping("history")
    public ResponseEntity<List<FareHistory>> getHistory() {
        List<FareHistory> fareHistories = fareHistoryService.getAllDrivers();
        return ResponseEntity.ok(fareHistories);
    }
}
