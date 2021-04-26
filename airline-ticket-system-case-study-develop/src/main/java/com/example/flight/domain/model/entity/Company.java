package com.example.flight.domain.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

    private String name;
    private String companyCode;

}
