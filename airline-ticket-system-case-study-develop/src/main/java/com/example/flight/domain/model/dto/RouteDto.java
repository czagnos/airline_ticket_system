package com.example.flight.domain.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private String uid;
    private AirportDto origin;
    private AirportDto destination;
}
