package com.example.flight.domain.exception;

public class CompanyNotFoundException extends BusinessException {

    public CompanyNotFoundException() {
        super("service.exception.company.not.found");
    }
}
