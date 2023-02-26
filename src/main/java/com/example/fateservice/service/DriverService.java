package com.example.fateservice.service;

import com.example.fateservice.dto.FareCalculationDTO;
import com.example.fateservice.entity.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver getDriverById(Long id);

    Driver saveDriver(Driver driver);

    void deleteDriver(Long id);

    Driver updateDriver(Driver driver);

    Driver findFareMin(FareCalculationDTO fareCalculationDTO);
}
