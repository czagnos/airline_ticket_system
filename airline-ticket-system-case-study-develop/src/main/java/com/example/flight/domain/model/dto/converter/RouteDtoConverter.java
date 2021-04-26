package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.entity.Route;
import org.mapstruct.Mapper;

@Mapper
public interface RouteDtoConverter {

    RouteDto toDto(Route route);
}
