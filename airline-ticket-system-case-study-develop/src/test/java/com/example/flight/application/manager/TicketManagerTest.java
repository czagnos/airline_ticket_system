package com.example.flight.application.manager;


import com.example.flight.application.model.request.BuyTicketRequest;
import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.request.converter.BuyTicketRequestConverter;
import com.example.flight.application.model.request.converter.CreateRouteRequestConverter;
import com.example.flight.application.model.response.RouteResponse;
import com.example.flight.application.model.response.TicketResponse;
import com.example.flight.domain.model.dto.*;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.model.vo.BuyTicketVo;
import com.example.flight.domain.service.RouteService;
import com.example.flight.domain.service.TicketBuyService;
import com.example.flight.domain.service.TicketCancelService;
import com.example.flight.domain.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TicketManagerTest {

    @InjectMocks
    TicketManager ticketManager;

    @Mock
    TicketService ticketService;

    @Mock
    TicketBuyService ticketBuyService;

    @Mock
    TicketCancelService ticketCancelService;

    @Mock
    BuyTicketRequestConverter buyTicketRequestConverter;


    @Test
    public  void retrieveTicketByPnrCodeTest(){
        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(new AirportDto("SAW","Sabiha"));
        routeDto.setDestination(new AirportDto("IST","Istanbul"));

        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber("TK2021");
        flightDto.setCapacity(10);
        flightDto.setBasePrice(BigDecimal.TEN);
        flightDto.setCompany(new CompanyDto("THY","TK"));
        flightDto.setRoute(routeDto);

        TicketDto ticketDto = new TicketDto();
        ticketDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticketDto.setCreditCardNumber("1234123412341324");
        ticketDto.setPrice(BigDecimal.TEN);
        ticketDto.setPnrCode("ABC123");
        ticketDto.setStatus(TicketStatus.ACTIVE);
        ticketDto.setMember(new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","John","Smith"));
        ticketDto.setFlight(flightDto);

        when(ticketService.retrieveTicketDto("ABC123"))
                .thenReturn(ticketDto);

        TicketResponse ticketResponse = ticketManager.retrieveTicket("ABC123");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getUid());
        assertEquals("1234123412341324",ticketResponse.getTicket().getCreditCardNumber());
        assertEquals(BigDecimal.TEN,ticketResponse.getTicket().getPrice());
        assertEquals("ABC123",ticketResponse.getTicket().getPnrCode());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.getTicket().getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getMember().getUid());
        assertEquals("TK2021",ticketResponse.getTicket().getFlight().getFlightNumber());


    }

    @Test
    public  void buyTicketTest(){
        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(new AirportDto("SAW","Sabiha"));
        routeDto.setDestination(new AirportDto("IST","Istanbul"));

        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber("TK2021");
        flightDto.setCapacity(10);
        flightDto.setBasePrice(BigDecimal.TEN);
        flightDto.setCompany(new CompanyDto("THY","TK"));
        flightDto.setRoute(routeDto);

        TicketDto ticketDto = new TicketDto();
        ticketDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticketDto.setCreditCardNumber("1234123412341324");
        ticketDto.setPrice(BigDecimal.TEN);
        ticketDto.setPnrCode("ABC123");
        ticketDto.setStatus(TicketStatus.ACTIVE);
        ticketDto.setMember(new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","John","Smith"));
        ticketDto.setFlight(flightDto);



        BuyTicketVo buyTicketVo = new BuyTicketVo("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","TK2021","1234123412341234");

        when(buyTicketRequestConverter.toVo(new BuyTicketRequest("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","TK2021","1234123412341234")))
                .thenReturn(buyTicketVo);
        when(ticketBuyService.buyTicket(buyTicketVo)).thenReturn(ticketDto);

        TicketResponse ticketResponse = ticketManager.buyTicket(new BuyTicketRequest("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","TK2021","1234123412341234"));

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getUid());
        assertEquals("1234123412341324",ticketResponse.getTicket().getCreditCardNumber());
        assertEquals(BigDecimal.TEN,ticketResponse.getTicket().getPrice());
        assertEquals("ABC123",ticketResponse.getTicket().getPnrCode());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.getTicket().getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getMember().getUid());
        assertEquals("TK2021",ticketResponse.getTicket().getFlight().getFlightNumber());
        verify(ticketBuyService, times(1)).buyTicket(buyTicketVo);
    }

    @Test
    public  void cancelTicketByPnrCodeTest(){
        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(new AirportDto("SAW","Sabiha"));
        routeDto.setDestination(new AirportDto("IST","Istanbul"));

        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber("TK2021");
        flightDto.setCapacity(10);
        flightDto.setBasePrice(BigDecimal.TEN);
        flightDto.setCompany(new CompanyDto("THY","TK"));
        flightDto.setRoute(routeDto);

        TicketDto ticketDto = new TicketDto();
        ticketDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticketDto.setCreditCardNumber("1234123412341324");
        ticketDto.setPrice(BigDecimal.TEN);
        ticketDto.setPnrCode("ABC123");
        ticketDto.setStatus(TicketStatus.CANCELLED);
        ticketDto.setMember(new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","John","Smith"));
        ticketDto.setFlight(flightDto);

        when(ticketCancelService.cancelTicket("ABC123"))
                .thenReturn(ticketDto);

        TicketResponse ticketResponse = ticketManager.cancelTicket("ABC123");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getUid());
        assertEquals("1234123412341324",ticketResponse.getTicket().getCreditCardNumber());
        assertEquals(BigDecimal.TEN,ticketResponse.getTicket().getPrice());
        assertEquals("ABC123",ticketResponse.getTicket().getPnrCode());
        assertEquals(TicketStatus.CANCELLED,ticketResponse.getTicket().getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getTicket().getMember().getUid());
        assertEquals("TK2021",ticketResponse.getTicket().getFlight().getFlightNumber());


    }
}
