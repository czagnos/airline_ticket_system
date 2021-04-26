package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.CreateAirportRequest;
import com.example.flight.domain.model.vo.AddAirportVo;
import org.mapstruct.Mapper;

@Mapper
public interface CreateAirportRequestConverter {

    AddAirportVo toVo(CreateAirportRequest request);
}
