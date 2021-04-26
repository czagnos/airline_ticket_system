package com.example.flight.application.manager;


import com.example.flight.application.model.request.CreateFlightRequest;
import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.request.converter.CreateFlightRequestConverter;
import com.example.flight.application.model.request.converter.CreateRouteRequestConverter;
import com.example.flight.application.model.response.FlightResponse;
import com.example.flight.application.model.response.RouteResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.FlightDto;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.vo.AddFlightVo;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.service.FlightService;
import com.example.flight.domain.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FlightManagerTest {

    @InjectMocks
    FlightManager flightManager;

    @Mock
    FlightService flightService;

    @Mock
    CreateFlightRequestConverter createFlightRequestConverter;


    @Test
    public  void retrieveFlightByFlightNumberTest(){
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


        when(flightService.retrieveFlightDto("TK2021"))
                .thenReturn(flightDto);

        FlightResponse flightResponse = flightManager.retrieveFlight("TK2021");

        assertEquals("TK2021",flightResponse.getFlight().getFlightNumber());
        assertEquals((Integer.valueOf(10)),flightResponse.getFlight().getCapacity());
        assertEquals(BigDecimal.TEN,flightResponse.getFlight().getBasePrice());
        assertEquals("TK",flightResponse.getFlight().getCompany().getCompanyCode());
        assertEquals("SAW",flightResponse.getFlight().getRoute().getOrigin().getIataCode());
        assertEquals("IST",flightResponse.getFlight().getRoute().getDestination().getIataCode());

    }

    @Test
    public  void addFlightTest(){
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

        AddFlightVo addFlightVo = new AddFlightVo();
        addFlightVo.setFlightNumber("TK2021");
        addFlightVo.setCapacity(10);
        addFlightVo.setBasePrice(BigDecimal.TEN);
        addFlightVo.setCompanyCode("TK");
        addFlightVo.setRouteUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        when(createFlightRequestConverter
                .toVo(new CreateFlightRequest("TK2021",BigDecimal.TEN,
                        10,"TK","5638a6f2-4de8-4d1c-9518-a7c3f8b0daae")))
                .thenReturn(addFlightVo);
        when(flightService.addFlight(addFlightVo)).thenReturn(flightDto);

        FlightResponse flightResponse = flightManager.addFlight(new CreateFlightRequest("TK2021",BigDecimal.TEN,
                10,"TK","5638a6f2-4de8-4d1c-9518-a7c3f8b0daae"));

        assertEquals("TK2021",flightResponse.getFlight().getFlightNumber());
        assertEquals((Integer.valueOf(10)),flightResponse.getFlight().getCapacity());
        assertEquals(BigDecimal.TEN,flightResponse.getFlight().getBasePrice());
        assertEquals("TK",flightResponse.getFlight().getCompany().getCompanyCode());
        assertEquals("SAW",flightResponse.getFlight().getRoute().getOrigin().getIataCode());
        assertEquals("IST",flightResponse.getFlight().getRoute().getDestination().getIataCode());
        verify(flightService, times(1)).addFlight(addFlightVo);
    }


}
