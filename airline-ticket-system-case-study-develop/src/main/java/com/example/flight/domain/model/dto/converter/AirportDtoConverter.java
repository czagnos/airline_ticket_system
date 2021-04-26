package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.entity.Airport;
import org.mapstruct.Mapper;

@Mapper
public interface AirportDtoConverter {

    AirportDto toDto(Airport airport);
}
