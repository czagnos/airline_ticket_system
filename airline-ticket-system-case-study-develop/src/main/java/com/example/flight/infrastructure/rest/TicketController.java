package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.TicketManager;
import com.example.flight.application.model.request.BuyTicketRequest;
import com.example.flight.application.model.response.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketManager ticketManager;

    @GetMapping("/v1/ticket/{pnrCode}")
    public TicketResponse retrieveTicket(@PathVariable String pnrCode) {
        return ticketManager.retrieveTicket(pnrCode);
    }

    @PostMapping("/v1/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse buyTicket(@RequestBody BuyTicketRequest request) {
        return ticketManager.buyTicket(request);
    }

    @PostMapping("/v1/ticket/{pnrCode}/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TicketResponse cancelTicket(@PathVariable String pnrCode) {
        return ticketManager.cancelTicket(pnrCode);
    }

}
