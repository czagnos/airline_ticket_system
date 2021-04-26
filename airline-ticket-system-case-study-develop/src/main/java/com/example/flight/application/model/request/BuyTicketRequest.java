package com.example.flight.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyTicketRequest {

    private String memberUid;
    private String flightNumber;
    private String creditCardNumber;
    //private boolean saveCreditCard;

}
