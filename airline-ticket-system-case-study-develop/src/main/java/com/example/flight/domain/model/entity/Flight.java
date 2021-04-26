package com.example.flight.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Table(name = "flight")
@Entity
public class Flight extends BaseEntity {

    private String flightNumber;

    private Integer capacity;

    private BigDecimal basePrice;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> ticket;

}
