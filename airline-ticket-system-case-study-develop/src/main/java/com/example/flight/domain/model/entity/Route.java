package com.example.flight.domain.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "route")
@Entity
public class Route extends BaseEntity {

    private String uid;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", referencedColumnName = "id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "id")
    private Airport destination;

}
