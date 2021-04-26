package com.example.flight.domain.model.entity;

import com.example.flight.domain.model.entity.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "ticket")
@Entity
public class Ticket extends BaseEntity{

    private String uid;

    private TicketStatus status;

    private String pnrCode;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    private String creditCardNumber;
}
