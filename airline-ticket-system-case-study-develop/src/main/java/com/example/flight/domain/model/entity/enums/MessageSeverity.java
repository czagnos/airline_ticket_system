package com.example.flight.domain.model.entity.enums;

public enum MessageSeverity implements ValueEnum<Integer> {

    ERROR(1);

    private final Integer value;

    MessageSeverity(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
