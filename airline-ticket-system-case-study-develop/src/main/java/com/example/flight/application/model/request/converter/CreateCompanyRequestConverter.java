package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.CreateCompanyRequest;
import com.example.flight.domain.model.vo.AddCompanyVo;
import org.mapstruct.Mapper;

@Mapper
public interface CreateCompanyRequestConverter {

    AddCompanyVo toVo(CreateCompanyRequest request);
}
