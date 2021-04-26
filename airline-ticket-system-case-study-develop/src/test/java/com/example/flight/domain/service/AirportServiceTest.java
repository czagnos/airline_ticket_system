package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.converter.AirportDtoConverter;
import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.repository.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AirportServiceTest {

    @InjectMocks
    AirportService airportService;

    @Mock
    AirportRepository airportRepository;

    @Mock
    AirportDtoConverter airportDtoConverter;

    private Airport airport;


    @Before
    public void setUp() {
        Airport airport = new Airport();
        airport.setId((long)1);
        airport.setIataCode("IST");
        airport.setName("Istanbul");
        this.airport = airport;
    }


    @Test
    public  void retrieveAirportByIataCodeTest(){
        AirportDto airportDto = new AirportDto("IST","Istanbul");

        when(airportRepository.findByIataCode("IST")).thenReturn(Optional.of(airport));
        when(airportDtoConverter.toDto(airport)).thenReturn(airportDto);


        AirportDto airportResponse = airportService.retrieveAirportDtoByIataCode("IST");

        assertEquals("IST",airportResponse.getIataCode());
        assertEquals("Istanbul",airportResponse.getName());

    }

    @Test
    public  void retrieveAirportTest(){
        when(airportRepository.findByIataCode("IST")).thenReturn(Optional.of(airport));

        Airport airportResponse = airportService.retrieveAirport("IST");

        assertEquals("IST",airportResponse.getIataCode());
        assertEquals("Istanbul",airportResponse.getName());
    }

    @Test
    public  void addAirportTest(){

        AirportDto airportDto = new AirportDto("IST","Istanbul");
        when(airportRepository.findByIataCode("IST")).thenReturn(Optional.of(airport));

        when(airportDtoConverter.toDto(airport)).thenReturn(airportDto);

        AirportDto airportResponse = airportService.addAirport(new AddAirportVo("Istanbul","IST"));

        assertEquals("IST",airportResponse.getIataCode());
        assertEquals("Istanbul",airportResponse.getName());
    }




}
