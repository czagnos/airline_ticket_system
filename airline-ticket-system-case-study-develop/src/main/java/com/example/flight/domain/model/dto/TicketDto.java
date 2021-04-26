package com.example.flight.domain.model.dto;

import com.example.flight.domain.model.entity.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private String uid;
    private TicketStatus status;
    private String pnrCode;
    private BigDecimal price;
    private FlightDto flight;
    private MemberDto member;
    private String creditCardNumber;

}
