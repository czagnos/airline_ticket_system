package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.*;
import com.example.flight.domain.model.dto.converter.TicketDtoConverter;
import com.example.flight.domain.model.entity.*;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.repository.TicketRepository;
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
public class TicketServiceTest {

    @InjectMocks
    TicketService ticketService;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    TicketDtoConverter ticketDtoConverter;

    private Ticket ticket;

    private TicketDto ticketDto;

    private Member member;

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

        Member member = new Member();
        member.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        member.setFirstName("John");
        member.setSurname("Smith");

        this.member = member;

        Flight flight = new Flight();
        flight.setFlightNumber("TK2021");
        flight.setCapacity(10);
        flight.setBasePrice(BigDecimal.TEN);
        flight.setCompany(company);
        flight.setRoute(route);

        this.flight = flight;

        Ticket ticket = new Ticket();
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


    }

    @Test
    public  void retrieveTicketDtoByPnrCodeTest(){

        when(ticketRepository.findByPnrCode("ABC123")).thenReturn(Optional.of(ticket));
        when(ticketDtoConverter.toDto(ticket)).thenReturn(ticketDto);


        TicketDto ticketResponse = ticketService.retrieveTicketDto("ABC123");


        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getUid());
        assertEquals("TK2021",ticketResponse.getFlight().getFlightNumber());
        assertEquals("ABC123",ticketResponse.getPnrCode());
        assertEquals(BigDecimal.TEN,ticketResponse.getPrice());
        assertEquals("1234132412341324",ticketResponse.getCreditCardNumber());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getMember().getUid());


    }

    @Test
    public  void retrieveTicketByPnrCodeTest(){

        when(ticketRepository.findByPnrCode("ABC123")).thenReturn(Optional.of(ticket));

        Ticket ticketResponse = ticketService.retrieveTicket("ABC123");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getUid());
        assertEquals("TK2021",ticketResponse.getFlight().getFlightNumber());
        assertEquals("ABC123",ticketResponse.getPnrCode());
        assertEquals(BigDecimal.TEN,ticketResponse.getPrice());
        assertEquals("1234132412341324",ticketResponse.getCreditCardNumber());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.getMember().getUid());



    }

    @Test
    public  void retrieveActiveTicketByFlightNumberAndMemberUidTest(){

        when(ticketRepository.findByFlight_FlightNumberAndAndMember_UidAndStatus("TK2021", "5638a6f2-4de8-4d1c-9518-a7c3f8b0daae", TicketStatus.ACTIVE))
                .thenReturn(Optional.of(ticket));

        Optional<Ticket> ticketResponse = ticketService.retrieveActiveTicketByFlightNumberAndMemberUid("TK2021", "5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.get().getUid());
        assertEquals("TK2021",ticketResponse.get().getFlight().getFlightNumber());
        assertEquals("ABC123",ticketResponse.get().getPnrCode());
        assertEquals(BigDecimal.TEN,ticketResponse.get().getPrice());
        assertEquals("1234132412341324",ticketResponse.get().getCreditCardNumber());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.get().getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.get().getMember().getUid());



    }



    @Test
    public  void updateCancelTicketTest(){

        when(ticketRepository.findByPnrCode("ABC123")).thenReturn(Optional.of(ticket));
        ticket.setStatus(TicketStatus.CANCELLED);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket ticketResponse = ticketService.updateCancelTicket("ABC123");

        assertEquals(TicketStatus.CANCELLED,ticketResponse.getStatus());

    }

    @Test
    public  void findByPnrCodeTest(){
        when(ticketRepository.findByPnrCodeAndStatus("ABC123",TicketStatus.ACTIVE))
                .thenReturn(Optional.of(ticket));

        Optional<Ticket> ticketResponse = ticketService.findByPnrCode("ABC123");


        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.get().getUid());
        assertEquals("TK2021",ticketResponse.get().getFlight().getFlightNumber());
        assertEquals("ABC123",ticketResponse.get().getPnrCode());
        assertEquals(BigDecimal.TEN,ticketResponse.get().getPrice());
        assertEquals("1234132412341324",ticketResponse.get().getCreditCardNumber());
        assertEquals(TicketStatus.ACTIVE,ticketResponse.get().getStatus());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",ticketResponse.get().getMember().getUid());

    }


}
