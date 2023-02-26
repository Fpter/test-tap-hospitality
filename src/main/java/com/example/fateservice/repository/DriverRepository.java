package com.example.fateservice.repository;

import com.example.fateservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);

    @Query(value = "SELECT top 1 *, BASE_FARE_PRICE + GREATEST(0, ((?1 - BASE_FARE_DISTANCE) / ?2 * ?3)) as total\n" +
                "FROM DRIVER\n" +
                "order by total", nativeQuery = true)
    Optional<Driver> findFareMin(double distance, double unit, double cost);
}