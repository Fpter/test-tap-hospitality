package com.example.fateservice.dto;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class DriverDTO implements java.io.Serializable {

    @Id
    private Long id;

    private String name;

    private String email;

    private String vehicleType;

    private Double baseFarePrice;

    private Double baseFareDistance;

    private Double total;
}
