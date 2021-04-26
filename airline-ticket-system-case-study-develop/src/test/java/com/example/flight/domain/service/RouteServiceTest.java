package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.dto.converter.CompanyDtoConverter;
import com.example.flight.domain.model.dto.converter.RouteDtoConverter;
import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.entity.Company;
import com.example.flight.domain.model.entity.Route;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.repository.CompanyRepository;
import com.example.flight.domain.repository.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    @InjectMocks
    RouteService routeService;

    @Mock
    RouteRepository routeRepository;

    @Mock
    RouteDtoConverter routeDtoConverter;

    private Route route;

    private RouteDto routeDto;

    @Before
    public void setUp() {

        Airport airportOrigin = new Airport();
        airportOrigin.setName("Sabiha");
        airportOrigin.setIataCode("SAW");

        Airport airportDestination = new Airport();
        airportDestination.setName("Istanbul");
        airportDestination.setIataCode("IST");

        Route route = new Route();
        route.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        route.setOrigin(airportOrigin);
        route.setDestination(airportDestination);
        this.route = route;

        AirportDto airportOriginDto = new AirportDto();
        airportOriginDto.setName("Sabiha");
        airportOriginDto.setIataCode("SAW");

        AirportDto airportDestinationDto = new AirportDto();
        airportDestinationDto.setName("Istanbul");
        airportDestinationDto.setIataCode("IST");

        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(airportOriginDto);
        routeDto.setDestination(airportDestinationDto);
        this.routeDto = routeDto;

    }

    @Test
    public  void retrieveRouteDtoByOriginAndDestinationTest(){

        when(routeRepository.findByOriginIataCodeAndDestinationIataCode("SAW","IST"))
                .thenReturn(Optional.of(route));

        when(routeDtoConverter.toDto(route)).thenReturn(routeDto);


        RouteDto routeResponse = routeService.retrieveRouteDto("SAW","IST");

        assertEquals("SAW",routeResponse.getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getDestination().getIataCode());

    }

    @Test
    public  void retrieveRouteTest(){

        when(routeRepository.findByOriginIataCodeAndDestinationIataCode("SAW","IST")).thenReturn(Optional.of(route));

        Route routeResponse = routeService.retrieveRoute("SAW","IST");

        assertEquals("SAW",routeResponse.getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getDestination().getIataCode());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",routeResponse.getUid());
    }

    @Test
    public  void retrieveRouteByUidTest(){

        when(routeRepository.findByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae")).thenReturn(Optional.of(route));

        Route routeResponse = routeService.retrieveRouteByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        assertEquals("SAW",routeResponse.getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getDestination().getIataCode());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",routeResponse.getUid());
    }

    @Test
    public  void addRouteTest(){

        when(routeRepository.findByOriginIataCodeAndDestinationIataCode("SAW","IST")).thenReturn(Optional.of(route));

        when(routeDtoConverter.toDto(route)).thenReturn(routeDto);

        RouteDto routeResponse = routeService.addRoute(new AddRouteVo("SAW","IST"));

        assertEquals("SAW",routeResponse.getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getDestination().getIataCode());
        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",routeResponse.getUid());

    }


}
