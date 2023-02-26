package com.example.fateservice.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "Driver")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String vehicleType;

    @NotNull
    private Double baseFarePrice;

    @NotNull
    private Double baseFareDistance;
}
