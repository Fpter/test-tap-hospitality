package com.example.fateservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FareCalculationDTO {
    private double distanceTraveled;
    private double traveledUnit;
    private double costPerDistanceTraveled;

}
