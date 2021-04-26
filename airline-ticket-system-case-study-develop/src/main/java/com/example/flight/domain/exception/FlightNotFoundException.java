package com.example.flight.domain.exception;

public class FlightNotFoundException extends BusinessException {

    public FlightNotFoundException() {
        super("service.exception.flight.not.found");
    }
}
