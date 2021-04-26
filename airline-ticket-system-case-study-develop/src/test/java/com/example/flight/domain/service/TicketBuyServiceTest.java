package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.*;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;
import com.example.flight.domain.model.entity.*;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.model.vo.BuyTicketVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TicketBuyServiceTest {

     @InjectMocks
     TicketBuyService ticketBuyService;


     @Mock
     TicketService ticketService;

     @Mock
     MemberService memberService;

     @Mock
     FlightService flightService;

     @Mock
     TicketDtoConverter ticketDtoConverter;

     @Mock
     TicketPriceService ticketPriceService;


    private Ticket ticket;

    private BuyTicketVo buyTicketVo;

    private  TicketDto ticketDto;

    private  Flight flight;

    private  Member member;

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
        flight.setId((long) 1);

        this.flight = flight;

        Member member = new Member();
        member.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        member.setFirstName("John");
        member.setSurname("Smith");
        member.setIdentityNumber("123456789123");
        member.setId((long) 1);

        this.member =  member;

        Ticket ticket = new Ticket();
        ticket.setId((long)1);
        ticket.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticket.setCreditCardNumber("1234132412341324");
        ticket.setFlight(flight);
        ticket.setMember(member);
        ticket.setPnrCode("ABC123");
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setPrice(BigDecimal.TEN);

        this.ticket = ticket;

        MemberDto memberDto = new MemberDto();
        memberDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        memberDto.setFirstName("John");
        memberDto.setSurname("Smith");

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
        ticketDto.setCreditCardNumber("1234132412341324");
        ticketDto.setFlight(flightDto);
        ticketDto.setMember(memberDto);
        ticketDto.setPnrCode("ABC123");
        ticketDto.setStatus(TicketStatus.ACTIVE);
        ticketDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticketDto.setPrice(BigDecimal.TEN);

        this.ticketDto  = ticketDto;

        this.buyTicketVo = new BuyTicketVo("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae","TK2021","1234132412341324");

    }

    @Test
    public  void buyTicketTest(){
        when(flightService.retrieveFlight("TK2021")).thenReturn(flight);
        ticket.setFlight(flight);
        when(memberService.retrieveMemberByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae")).thenReturn(member);
        ticket.setMember(member);
        BigDecimal price = BigDecimal.TEN;
        when(ticketPriceService.getCurrentPriceForFlight("TK2021")).thenReturn(price);
        String creditCardNumber = "123413******1324";
        when(ticketService.createTicket(flight,member,price,creditCardNumber)).thenReturn(ticket);
        when(ticketDtoConverter.toDto(ticket)).thenReturn(ticketDto);

        TicketDto ticketResponse = ticketBuyService.buyTicket(buyTicketVo);

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getUid());
        assertEquals("TK2021",ticketResponse.getFlight().getFlightNumber());
        assertEquals("ABC123",ticketResponse.getPnrCode());
        assertEquals(BigDecimal.TEN,ticketResponse.getPrice());
        assertEquals("1234132412341324",ticketResponse.getCreditCardNumber());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getMember().getUid());

    }


}
