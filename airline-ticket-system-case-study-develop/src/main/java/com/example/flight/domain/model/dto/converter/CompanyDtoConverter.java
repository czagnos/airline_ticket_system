package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyDtoConverter {

    CompanyDto toDto(Company company);
}
