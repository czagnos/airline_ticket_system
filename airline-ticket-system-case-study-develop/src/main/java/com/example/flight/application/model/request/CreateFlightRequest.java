package com.example.flight.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightRequest {

    private String flightNumber;
    private BigDecimal basePrice;
    private Integer capacity;
    private String companyCode;
    private String routeUid;

}
