package com.example.flight.domain.repository;


import com.example.flight.domain.model.entity.Ticket;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByPnrCode (String pnrCode);

    Optional<Ticket> findByFlight_FlightNumberAndAndMember_UidAndStatus(String flightNumber, String citizenId, TicketStatus status);

    Optional<Ticket> findByPnrCodeAndStatus(String pnrCode , TicketStatus status);
}
