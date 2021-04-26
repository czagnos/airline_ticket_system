package com.example.flight.domain.service;

import com.example.flight.domain.model.entity.Flight;
import com.example.flight.utils.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketPriceService {

    private final FlightService flightService;



    public BigDecimal getCurrentPriceForFlight(String fightNumber) {
        Flight flight = flightService.retrieveFlight(fightNumber);
        BigDecimal basePrice = flight.getBasePrice();
        Integer flightCapacity = flight.getCapacity();
        flightService.validateFlightCapacity(flightCapacity,fightNumber);
        Integer passengerCount = flightService.getPassengerCountForFlight(fightNumber);
        BigDecimal currentPrice = PriceCalculator.calculatePrice( flightCapacity ,  passengerCount,  basePrice);
        return  currentPrice;
    }




}
