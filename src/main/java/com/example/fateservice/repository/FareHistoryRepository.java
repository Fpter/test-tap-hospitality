package com.example.fateservice.repository;

import com.example.fateservice.entity.FareHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FareHistoryRepository extends JpaRepository<FareHistory, Long> {

}
