package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MemberDtoConverter {

    MemberDto toDto(Member member);

    List<MemberDto> toDto(List<Member> member);
}
