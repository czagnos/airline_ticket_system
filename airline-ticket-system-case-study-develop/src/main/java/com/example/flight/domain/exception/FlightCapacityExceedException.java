package com.example.flight.domain.exception;

public class FlightCapacityExceedException extends BusinessException {

    public FlightCapacityExceedException() {
        super("service.exception.flight.capacity.exceed");
    }
}
