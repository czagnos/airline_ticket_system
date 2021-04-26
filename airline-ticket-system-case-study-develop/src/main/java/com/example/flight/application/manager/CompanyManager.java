package com.example.flight.application.manager;

import com.example.flight.application.model.request.CreateCompanyRequest;
import com.example.flight.application.model.request.converter.CreateCompanyRequestConverter;
import com.example.flight.application.model.response.CompanyResponse;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyManager {

    private final CompanyService companyService;
    private final CreateCompanyRequestConverter createCompanyRequestConverter;

    public CompanyResponse retrieveCompany(String companyCode) {
        CompanyDto company = companyService.retrieveCompanyDto(companyCode);

        return CompanyResponse.builder()
                .company(company)
                .build();
    }

    public CompanyResponse addCompany(CreateCompanyRequest request) {
        AddCompanyVo addCompanyVo = createCompanyRequestConverter.toVo(request);
        CompanyDto company = companyService.addCompany(addCompanyVo);

        return CompanyResponse.builder()
                .company(company)
                .build();
    }
}
