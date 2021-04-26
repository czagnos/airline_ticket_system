package com.example.flight.domain.exception;

public class TicketAlreadyBoughtException extends BusinessException {

    public TicketAlreadyBoughtException() {
        super("service.exception.ticket.already.bought");
    }
}
