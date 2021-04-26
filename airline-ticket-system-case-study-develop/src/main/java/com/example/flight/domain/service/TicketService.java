package com.example.flight.domain.service;

import com.example.flight.domain.exception.TicketNotFoundException;
import com.example.flight.domain.model.dto.TicketDto;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;
import com.example.flight.domain.model.entity.Flight;
import com.example.flight.domain.model.entity.Member;
import com.example.flight.domain.model.entity.Ticket;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.repository.TicketRepository;
import com.example.flight.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketDtoConverter ticketDtoConverter;


    public TicketDto retrieveTicketDto(String pnrCode) {
        Ticket ticket = retrieveTicket(pnrCode);
        return ticketDtoConverter.toDto(ticket);
    }

    public Ticket retrieveTicket(String pnrCode) {
        return ticketRepository.findByPnrCode(pnrCode)
                .orElseThrow(TicketNotFoundException::new);
    }


    public Optional<Ticket> retrieveActiveTicketByFlightNumberAndMemberUid(String flightNumber, String memberUid) {
        return ticketRepository.findByFlight_FlightNumberAndAndMember_UidAndStatus(flightNumber, memberUid, TicketStatus.ACTIVE);
    }


    public Ticket createTicket(Flight flight, Member member, BigDecimal price, String creditCardNumber) {
        Ticket ticket = new Ticket();
        ticket.setUid(CodeGenerator.generateUuid());
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setPnrCode(CodeGenerator.generatePnrCode());
        ticket.setPrice(price);
        ticket.setFlight(flight);
        ticket.setMember(member);
        ticket.setCreditCardNumber(creditCardNumber);
        return ticketRepository.save(ticket);
    }


    public Ticket updateCancelTicket(String pnrCode) {
       Ticket ticket =  ticketRepository.findByPnrCode(pnrCode).orElseThrow(TicketNotFoundException::new);
        ticket.setStatus(TicketStatus.CANCELLED);
        Ticket cancelledTicket = ticketRepository.save(ticket);
        return cancelledTicket;
    }

    public Optional<Ticket> findByPnrCode(String pnrCode) {
        return ticketRepository.findByPnrCodeAndStatus(pnrCode,TicketStatus.ACTIVE);
    }
}
