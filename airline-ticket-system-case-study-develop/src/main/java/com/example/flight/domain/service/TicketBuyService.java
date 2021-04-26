package com.example.flight.domain.service;


import com.example.flight.domain.exception.TicketAlreadyBoughtException;
import com.example.flight.domain.model.dto.TicketDto;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;
import com.example.flight.domain.model.entity.Flight;
import com.example.flight.domain.model.entity.Member;
import com.example.flight.domain.model.entity.Ticket;
import com.example.flight.domain.model.vo.BuyTicketVo;
import com.example.flight.utils.CreditCardConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketBuyService {

    private final TicketService ticketService;
    private final MemberService memberService;
    private final FlightService flightService;
    private final TicketDtoConverter ticketDtoConverter;
    private final TicketPriceService ticketPriceService;

    public TicketDto buyTicket(BuyTicketVo buyTicketVo) {

        Optional<Ticket> optionalTicket = ticketService.retrieveActiveTicketByFlightNumberAndMemberUid(buyTicketVo.getFlightNumber(), buyTicketVo.getMemberUid());

        if (optionalTicket.isPresent()) {
            throw new TicketAlreadyBoughtException();
        } else {
            return recordBoughtTicket(buyTicketVo);
        }
    }

    private TicketDto recordBoughtTicket(BuyTicketVo buyTicketVo) {
        Flight flight = flightService.retrieveFlight(buyTicketVo.getFlightNumber());
        Member member = memberService.retrieveMemberByUid(buyTicketVo.getMemberUid());
        BigDecimal price = ticketPriceService.getCurrentPriceForFlight(flight.getFlightNumber());
        String creditCardNumber = CreditCardConfiguration.fixCreditCardNumber(buyTicketVo.getCreditCardNumber());
        creditCardNumber = CreditCardConfiguration.maskCard(creditCardNumber);
        flightService.setRemovePassengerCountForFlight(flight.getFlightNumber());
        Ticket ticket = ticketService.createTicket(flight, member, price,creditCardNumber);
        return ticketDtoConverter.toDto(ticket);
    }


}
