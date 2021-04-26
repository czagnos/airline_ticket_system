package com.example.flight.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "airport")
@Entity
public class Airport extends BaseEntity {

    private String iataCode;
    private String name;

    //todo location, country etc

}
