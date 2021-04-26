package com.example.flight.domain.exception;

public class AirportNotFoundException extends BusinessException {

    public AirportNotFoundException() {
        super("service.exception.airport.not.found");
    }
}
