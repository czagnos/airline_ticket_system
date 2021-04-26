package com.example.flight.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "member")
@Entity
public class Member extends BaseEntity{

    private String uid;
    private String identityNumber;
    private String firstName;
    private String surname;

}
