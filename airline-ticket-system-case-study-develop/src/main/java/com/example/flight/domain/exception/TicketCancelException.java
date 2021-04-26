package com.example.flight.domain.exception;

public class TicketCancelException extends BusinessException {

    public TicketCancelException() {
        super("service.exception.ticket.already.or.not.found");
    }
}
