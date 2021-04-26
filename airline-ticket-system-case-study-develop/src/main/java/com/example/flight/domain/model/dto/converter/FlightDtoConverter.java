package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.FlightDto;
import com.example.flight.domain.model.entity.Flight;
import org.mapstruct.Mapper;

@Mapper
public interface FlightDtoConverter {

    FlightDto toDto(Flight flight);
}
