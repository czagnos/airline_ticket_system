package com.example.flight.domain.service;

import com.example.flight.domain.exception.AirportNotFoundException;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.converter.AirportDtoConverter;
import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportDtoConverter airportDtoConverter;

    public AirportDto retrieveAirportDtoByIataCode(String iataCode) {
        Airport airport = retrieveAirport(iataCode);
        return airportDtoConverter.toDto(airport);
    }

    public Airport retrieveAirport(String iataCode) {
        return airportRepository.findByIataCode(iataCode)
                .orElseThrow(AirportNotFoundException::new);
    }

    public AirportDto addAirport(AddAirportVo addAirportVo) {
        Airport airport = airportRepository.findByIataCode(addAirportVo.getIataCode())
                .orElseGet(() -> saveAirport(addAirportVo));
        return airportDtoConverter.toDto(airport);
    }

    private Airport saveAirport(AddAirportVo addAirportVo) {
        Airport airport = new Airport();
        airport.setName(addAirportVo.getName());
        airport.setIataCode(addAirportVo.getIataCode());
        return airportRepository.save(airport);
    }

}
