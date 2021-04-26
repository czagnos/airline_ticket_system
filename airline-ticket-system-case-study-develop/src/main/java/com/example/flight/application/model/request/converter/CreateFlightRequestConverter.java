package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.CreateFlightRequest;
import com.example.flight.domain.model.vo.AddFlightVo;
import org.mapstruct.Mapper;

@Mapper
public interface CreateFlightRequestConverter {

    AddFlightVo toVo(CreateFlightRequest request);
}
