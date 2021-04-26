package com.example.flight.application.manager;


import com.example.flight.application.model.request.CreateAirportRequest;
import com.example.flight.application.model.request.converter.CreateAirportRequestConverter;
import com.example.flight.application.model.response.AirportResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.service.AirportService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AirportManagerTest {

    @InjectMocks
    AirportManager airportManager;

    @Mock
    AirportService airportService;

    @Mock
    CreateAirportRequestConverter createAirportRequestConverter;


    @Test
    public  void retrieveAirportByIataCodeTest(){
        AirportDto airportDto = new AirportDto("IST","Istanbul");
        when(airportService.retrieveAirportDtoByIataCode("IST"))
                .thenReturn(airportDto);
        AirportResponse airportResponse = airportManager.retrieveAirport("IST");

        assertEquals("IST",airportResponse.getAirport().getIataCode());
        assertEquals("Istanbul",airportResponse.getAirport().getName());

    }

    @Test
    public  void addAirportTest(){
        AirportDto airportDto = new AirportDto("IST","Istanbul");
        AddAirportVo addAirportVo = new AddAirportVo("IST","Istanbul");
        when(createAirportRequestConverter.toVo(new CreateAirportRequest("Istanbul","IST")))
                .thenReturn(addAirportVo);
        when(airportService.addAirport(addAirportVo)).thenReturn(airportDto);

        AirportResponse airportResponse = airportManager.addAirport(new CreateAirportRequest("Istanbul","IST"));

        assertEquals("IST",airportResponse.getAirport().getIataCode());
        assertEquals("Istanbul",airportResponse.getAirport().getName());
        verify(airportService, times(1)).addAirport(addAirportVo);
    }


}
