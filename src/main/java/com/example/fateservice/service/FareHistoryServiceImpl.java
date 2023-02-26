package com.example.fateservice.service;

import com.example.fateservice.entity.FareHistory;
import com.example.fateservice.repository.FareHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareHistoryServiceImpl implements FareHistoryService {

    @Autowired
    private FareHistoryRepository fareHistoryRepository;

    @Override
    public List<FareHistory> getAllDrivers() {
        return fareHistoryRepository.findAll();
    }

    @Override
    public FareHistory save(FareHistory fareHistory) {
        return fareHistoryRepository.save(fareHistory);

    }


}
