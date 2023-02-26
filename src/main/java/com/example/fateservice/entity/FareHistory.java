package com.example.fateservice.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "fare")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long driverId;


    @NotNull
    private Double curBaseFarePrice;

    @NotNull
    private Double curBaseFareDistance;
    @NotNull

    private Double distanceTraveled;
    @NotNull
    private Double traveledUnit;
    @NotNull
    private Double costPerDistanceTraveled;

    private Double total;
}
