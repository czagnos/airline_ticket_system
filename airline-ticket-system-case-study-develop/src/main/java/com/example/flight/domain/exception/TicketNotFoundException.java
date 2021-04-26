package com.example.flight.domain.exception;

public class TicketNotFoundException extends BusinessException {

    public TicketNotFoundException() {
        super("service.exception.ticket.not.found");
    }
}
