package com.example.flight.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddFlightVo {

    private String flightNumber;
    private BigDecimal basePrice;
    private Integer capacity;
    private String companyCode;
    private String routeUid;
}
