package com.example.flight.domain.service;


import com.example.flight.domain.exception.TicketCancelException;
import com.example.flight.domain.model.dto.TicketDto;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;

import com.example.flight.domain.model.entity.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketCancelService {

    private final TicketService ticketService;
    private final FlightService flightService;
    private final TicketDtoConverter ticketDtoConverter;

    public TicketDto cancelTicket(String pnrCode) {

        Optional<Ticket> optionalTicket = ticketService.findByPnrCode(pnrCode);

        if (optionalTicket.isPresent()) {
            return recordCancelledTicket(pnrCode);
        } else {
            throw new TicketCancelException();
        }
    }

    private TicketDto recordCancelledTicket(String pnrCode) {
       Ticket ticket =  ticketService.updateCancelTicket(pnrCode);
        flightService.setAddPassengerCountForFlight(ticket.getFlight().getFlightNumber());
        return ticketDtoConverter.toDto(ticket);
    }


}
