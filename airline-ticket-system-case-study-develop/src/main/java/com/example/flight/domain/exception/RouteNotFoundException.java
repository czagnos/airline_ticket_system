package com.example.flight.domain.exception;

public class RouteNotFoundException extends BusinessException {

    public RouteNotFoundException() {
        super("service.exception.route.not.found");
    }
}
