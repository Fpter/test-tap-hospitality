package com.example.fateservice.service;

import com.example.fateservice.dto.FareCalculationDTO;
import com.example.fateservice.entity.Driver;
import com.example.fateservice.exception.EmailAlreadyExistsException;
import com.example.fateservice.exception.ResourceNotFoundException;
import com.example.fateservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", id)
        );
    }

    public Driver updateDriver(Driver driver) {
        Driver driverToUpdate = driverRepository.findByEmail(driver.getEmail()).orElseThrow(
                () -> new EmailAlreadyExistsException("Email Already Exists for Driver")
        );
        driverToUpdate.setName(driver.getName());
        driverToUpdate.setVehicleType(driver.getVehicleType());
        driverToUpdate.setBaseFarePrice(driver.getBaseFarePrice());
        driverToUpdate.setBaseFareDistance(driver.getBaseFareDistance());
        return driverRepository.save(driverToUpdate);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Driver findFareMin(FareCalculationDTO fareCalculationDTO) {
        Driver driver = driverRepository.findFareMin(fareCalculationDTO.getDistanceTraveled(), fareCalculationDTO.getTraveledUnit(), fareCalculationDTO.getCostPerDistanceTraveled()).get();
        return driver;
    }

    public Driver saveDriver(Driver driver) {
        Optional<Driver> driverOptional = driverRepository.findByEmail(driver.getEmail());
        if (driverOptional.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for Driver");
        }
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", id)
        );
        driverRepository.deleteById(id);
    }
}
