package com.example.flight.application.manager;


import com.example.flight.application.model.request.CreateCompanyRequest;
import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.request.converter.CreateCompanyRequestConverter;
import com.example.flight.application.model.request.converter.CreateRouteRequestConverter;
import com.example.flight.application.model.response.CompanyResponse;
import com.example.flight.application.model.response.RouteResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.service.CompanyService;
import com.example.flight.domain.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RouteManagerTest {

    @InjectMocks
    RouteManager routeManager;

    @Mock
    RouteService routeService;

    @Mock
    CreateRouteRequestConverter createRouteRequestConverter;


    @Test
    public  void retrieveRouteByOriginAndDestinationTest(){
        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(new AirportDto("SAW","Sabiha"));
        routeDto.setDestination(new AirportDto("IST","Istanbul"));

        when(routeService.retrieveRouteDto("SAW","IST"))
                .thenReturn(routeDto);

        RouteResponse routeResponse = routeManager.retrieveRoute("SAW","IST");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",routeResponse.getRoute().getUid());
        assertEquals("SAW",routeResponse.getRoute().getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getRoute().getDestination().getIataCode());


    }

    @Test
    public  void addRouteTest(){
        RouteDto routeDto = new RouteDto();
        routeDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        routeDto.setOrigin(new AirportDto("SAW","Sabiha"));
        routeDto.setDestination(new AirportDto("IST","Istanbul"));

        AddRouteVo addRouteVo = new AddRouteVo("SAW","IST");

        when(createRouteRequestConverter.toVo(new CreateRouteRequest("SAW","IST")))
                .thenReturn(addRouteVo);
        when(routeService.addRoute(addRouteVo)).thenReturn(routeDto);

        RouteResponse routeResponse = routeManager.addRoute(new CreateRouteRequest("SAW","IST"));

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",routeResponse.getRoute().getUid());
        assertEquals("SAW",routeResponse.getRoute().getOrigin().getIataCode());
        assertEquals("IST",routeResponse.getRoute().getDestination().getIataCode());
        verify(routeService, times(1)).addRoute(addRouteVo);
    }


}
