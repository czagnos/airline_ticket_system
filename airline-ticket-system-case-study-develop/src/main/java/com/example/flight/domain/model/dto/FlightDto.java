package com.example.flight.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private String flightNumber;
    private BigDecimal basePrice;
    private Integer capacity;
    private CompanyDto company;
    private RouteDto route;
}
