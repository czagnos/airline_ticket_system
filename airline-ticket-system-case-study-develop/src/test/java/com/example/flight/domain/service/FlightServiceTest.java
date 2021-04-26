package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.FlightDto;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.dto.converter.CompanyDtoConverter;
import com.example.flight.domain.model.dto.converter.FlightDtoConverter;
import com.example.flight.domain.model.entity.*;
import com.example.flight.domain.model.entity.enums.TicketStatus;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.model.vo.AddFlightVo;
import com.example.flight.domain.repository.CompanyRepository;
import com.example.flight.domain.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @InjectMocks
    FlightService flightService;

    @Mock
    FlightRepository flightRepository;

    @Mock
    CompanyService companyService;

    @Mock
    RouteService routeService;

    @Mock
    FlightDtoConverter flightDtoConverter;

    private Flight flight;

    private FlightDto flightDto;

    private AddFlightVo addFlightVo;

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

        Flight flight = new Flight();
        flight.setFlightNumber("TK2021");
        flight.setCapacity(10);
        flight.setBasePrice(BigDecimal.TEN);
        flight.setCompany(company);
        flight.setRoute(route);

        Ticket ticket = new Ticket();
        ticket.setCreditCardNumber("1234132412341324");
        ticket.setFlight(flight);
        ticket.setMember(member);
        ticket.setPnrCode("ABC123");
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        ticket.setPrice(BigDecimal.TEN);

        Ticket ticket1 = ticket;
        Ticket ticket2 = ticket;
        Ticket ticket3 = ticket;
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        ticketList.add(ticket3);
        flight.setTicket(ticketList);
        this.flight = flight;

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

        this.flightDto = flightDto;

        AddFlightVo addFlightVo = new AddFlightVo();
        addFlightVo.setFlightNumber("TK2021");
        addFlightVo.setBasePrice(BigDecimal.TEN);
        addFlightVo.setCapacity(10);
        addFlightVo.setCompanyCode("TK");
        addFlightVo.setRouteUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        this.addFlightVo = addFlightVo;

    }

    @Test
    public  void retrieveFlightDtoByFlightNumberTest(){

        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));
        when(flightDtoConverter.toDto(flight)).thenReturn(flightDto);


        FlightDto flightResponse = flightService.retrieveFlightDto("TK2021");

        assertEquals("TK2021",flightResponse.getFlightNumber());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",flightResponse.getRoute().getUid());
        assertEquals(BigDecimal.TEN,flightResponse.getBasePrice());
        assertEquals(Integer.valueOf(10),flightResponse.getCapacity());
        assertEquals("TK",flightResponse.getCompany().getCompanyCode());


    }

    @Test
    public  void retrieveFlightByFlightNumberTest(){

        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));

        Flight flightResponse = flightService.retrieveFlight("TK2021");

        assertEquals("TK2021",flightResponse.getFlightNumber());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",flightResponse.getRoute().getUid());
        assertEquals(BigDecimal.TEN,flightResponse.getBasePrice());
        assertEquals(Integer.valueOf(10),flightResponse.getCapacity());
        assertEquals("TK",flightResponse.getCompany().getCompanyCode());


    }

    @Test
    public  void addFlightByTest(){

        when(flightRepository.findByFlightNumber(addFlightVo.getFlightNumber())).thenReturn(Optional.of(flight));

        Flight flightResponse = flightService.retrieveFlight("TK2021");

        assertEquals("TK2021",flightResponse.getFlightNumber());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",flightResponse.getRoute().getUid());
        assertEquals(BigDecimal.TEN,flightResponse.getBasePrice());
        assertEquals(Integer.valueOf(10),flightResponse.getCapacity());
        assertEquals("TK",flightResponse.getCompany().getCompanyCode());


    }

    @Test
    public  void getPassengerCountForFlightTest(){

        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));

        Integer flightResponse = flightService.getPassengerCountForFlight("TK2021");

        assertEquals(Integer.valueOf(flight.getTicket().size()),flightResponse);

    }

    @Test
    public  void setAddPassengerCountForFlightTest(){
        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));
        flightService.setAddPassengerCountForFlight("TK2021");
        assertEquals(Integer.valueOf(11),flight.getCapacity());
    }

    @Test
    public  void setRemovePassengerCountForFlightTest(){
        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));
        flightService.setRemovePassengerCountForFlight("TK2021");
        assertEquals(Integer.valueOf(9),flight.getCapacity());
    }

    @Test
    public  void validateFlightCapacityTest(){
        when(flightRepository.findByFlightNumber("TK2021")).thenReturn(Optional.of(flight));
        flightService.validateFlightCapacity(0,"TK2021");
    }

}
