package com.example.flight.domain.model.entity.enums;

public enum TicketStatus implements ValueEnum<Integer> {

    CANCELLED(-1),
    ACTIVE(1);

    private final Integer value;

    TicketStatus(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
