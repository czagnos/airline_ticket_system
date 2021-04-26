package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.domain.model.vo.AddRouteVo;
import org.mapstruct.Mapper;

@Mapper
public interface CreateRouteRequestConverter {

    AddRouteVo toVo(CreateRouteRequest request);
}
