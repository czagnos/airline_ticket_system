package com.example.flight.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

}
