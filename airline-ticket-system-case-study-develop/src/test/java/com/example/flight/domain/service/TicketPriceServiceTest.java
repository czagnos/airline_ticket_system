package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.*;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;
import com.example.flight.domain.model.entity.*;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.repository.TicketRepository;
import com.example.flight.utils.PriceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TicketPriceServiceTest {

    @InjectMocks
    TicketPriceService ticketPriceService;

    @Mock
    FlightService flightService;



    private Flight flight;

    @Before
    public void setUp() {
        Company company = new Company();
        company.setCompanyCode("TK");
        company.setName("THY");

        Airport airportOrigin = new Airport();
        airportOrigin.setIataCode("SAW");
        airportOrigin.setName("Sabiha");

        Airport airportDestination = new Airport();
        airportDestination.setIataCode("IST");
        airportDestination.setName("Istanbul");

        Route route = new Route();
        route.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        route.setOrigin(airportOrigin);
        route.setDestination(airportDestination);


        Flight flight = new Flight();
        flight.setFlightNumber("TK2021");
        flight.setCapacity(10);
        flight.setBasePrice(BigDecimal.TEN);
        flight.setCompany(company);
        flight.setRoute(route);

        this.flight = flight;

    }

    @Test
    public  void retrieveTicketDtoByPnrCodeTest(){
        MockedStatic<PriceCalculator> mockedStatic = Mockito.mockStatic(PriceCalculator.class);
        when(flightService.retrieveFlight("ABC123")).thenReturn(flight);
        BigDecimal basePrice = flight.getBasePrice();
        Integer flightCapacity = flight.getCapacity();
        Integer passengerCount = 10;
        when(flightService.getPassengerCountForFlight("ABC123")).thenReturn(passengerCount);
        BigDecimal currentPrice = BigDecimal.TEN;
        mockedStatic.when(() ->PriceCalculator
                .calculatePrice(flightCapacity,passengerCount,basePrice))
                .thenReturn(currentPrice);


        BigDecimal ticketResponse = ticketPriceService.getCurrentPriceForFlight("ABC123");


        assertEquals(currentPrice,ticketResponse);



    }


}
