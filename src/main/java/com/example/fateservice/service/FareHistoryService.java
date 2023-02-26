package com.example.fateservice.service;

import com.example.fateservice.entity.FareHistory;

import java.util.List;

public interface FareHistoryService {
    List<FareHistory> getAllDrivers();

    FareHistory save(FareHistory fareHistory);

}
