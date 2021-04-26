package com.example.flight.application.manager;

import com.example.flight.application.model.request.BuyTicketRequest;
import com.example.flight.application.model.request.converter.BuyTicketRequestConverter;
import com.example.flight.application.model.response.TicketResponse;
import com.example.flight.domain.model.dto.TicketDto;
import com.example.flight.domain.model.vo.BuyTicketVo;
import com.example.flight.domain.service.FlightService;
import com.example.flight.domain.service.TicketBuyService;
import com.example.flight.domain.service.TicketCancelService;
import com.example.flight.domain.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TicketManager {

    private final TicketService ticketService;
    private final TicketBuyService ticketBuyService;
    private final TicketCancelService ticketCancelService;
    private final BuyTicketRequestConverter buyTicketRequestConverter;

    public TicketResponse retrieveTicket(String pnrCode) {
        TicketDto ticket = ticketService.retrieveTicketDto(pnrCode);

        return TicketResponse.builder()
                .ticket(ticket)
                .build();
    }

    public TicketResponse buyTicket(BuyTicketRequest request) {
        BuyTicketVo buyTicketVo = buyTicketRequestConverter.toVo(request);
        TicketDto ticketDto = ticketBuyService.buyTicket(buyTicketVo);

        return TicketResponse.builder()
                .ticket(ticketDto)
                .build();
    }

    public TicketResponse cancelTicket(String pnrCode) {
        TicketDto ticket = ticketCancelService.cancelTicket(pnrCode);
        return TicketResponse.builder()
                .ticket(ticket)
                .build();
    }
}
