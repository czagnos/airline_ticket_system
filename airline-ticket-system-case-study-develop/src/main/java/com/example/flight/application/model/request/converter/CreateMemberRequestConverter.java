package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.CreateMemberRequest;
import com.example.flight.domain.model.vo.CreateMemberVo;
import org.mapstruct.Mapper;

@Mapper
public interface CreateMemberRequestConverter {

    CreateMemberVo toVo(CreateMemberRequest request);
}
