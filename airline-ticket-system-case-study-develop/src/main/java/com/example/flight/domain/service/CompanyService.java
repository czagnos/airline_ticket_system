package com.example.flight.domain.service;

import com.example.flight.domain.exception.CompanyNotFoundException;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.converter.CompanyDtoConverter;
import com.example.flight.domain.model.entity.Company;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDtoConverter companyDtoConverter;

    public CompanyDto retrieveCompanyDto(String companyCode) {
        Company company = retrieveCompany(companyCode);
        return companyDtoConverter.toDto(company);
    }

    public Company retrieveCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode)
                .orElseThrow(CompanyNotFoundException::new);
    }

    public CompanyDto addCompany(AddCompanyVo addCompanyVo) {
        Company company = companyRepository.findByCompanyCode(addCompanyVo.getCompanyCode())
                .orElseGet(() -> saveCompany(addCompanyVo));
        return companyDtoConverter.toDto(company);
    }

    private Company saveCompany(AddCompanyVo addCompanyVo) {
        Company company = new Company();
        company.setName(addCompanyVo.getName());
        company.setCompanyCode(addCompanyVo.getCompanyCode());
        return companyRepository.save(company);
    }

}
