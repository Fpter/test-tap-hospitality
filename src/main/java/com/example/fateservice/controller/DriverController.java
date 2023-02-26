package com.example.fateservice.controller;

import com.example.fateservice.entity.Driver;
import com.example.fateservice.exception.ResourceNotFoundException;
import com.example.fateservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * Get all drivers.
     *
     * @return ResponseEntity<List<Driver>>
     */
    @GetMapping("")
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok().body(drivers);
    }

    /**
     * Get driver by id
     *
     * @param id Long
     * @return ResponseEntity<Driver>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Driver driver = driverService.getDriverById(id);
        return ResponseEntity.ok().body(driver);
    }

    /**
     * Create new driver.
     *
     * @param driver Driver
     * @return ResponseEntity<Driver>
     */
    @PostMapping("")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver driverSaved = driverService.saveDriver(driver);
        return new ResponseEntity<>(driverSaved, HttpStatus.CREATED);
    }

    /**
     * Update infomation of driver.
     *
     * @param id Long
     * @param driverDetails Driver
     * @return ResponseEntity<Driver>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable(value = "id") Long id,
                                               @RequestBody Driver driverDetails) throws ResourceNotFoundException {
        Driver driver = driverService.updateDriver(driverDetails);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    /**
     * Delete driver by id.
     *
     * @param id Long
     * @return ResponseEntity<Driver>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriverByEmail(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok("Driver with id = " + id + " has been deleted.");
    }


}
