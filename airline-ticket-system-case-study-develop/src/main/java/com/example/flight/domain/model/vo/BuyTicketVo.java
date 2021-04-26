package com.example.flight.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyTicketVo {

    private String memberUid;
    private String flightNumber;
    private String creditCardNumber;
}
