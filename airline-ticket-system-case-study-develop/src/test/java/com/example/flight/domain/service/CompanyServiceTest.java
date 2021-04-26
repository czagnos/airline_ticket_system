package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.converter.AirportDtoConverter;
import com.example.flight.domain.model.dto.converter.CompanyDtoConverter;
import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.entity.Company;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.repository.AirportRepository;
import com.example.flight.domain.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    CompanyDtoConverter companyDtoConverter;

    private Company company;

    private CompanyDto companyDto;
    @Before
    public void setUp() {
        Company company = new Company();
        company.setId((long)1);
        company.setName("THY");
        company.setCompanyCode("TK");
        this.company = company;

        this.companyDto = new CompanyDto("THY","TK");
    }

    @Test
    public  void retrieveCompanyByCompanyCodeTest(){


        when(companyRepository.findByCompanyCode("TK")).thenReturn(Optional.of(company));
        when(companyDtoConverter.toDto(company)).thenReturn(companyDto);


        CompanyDto companyResponse = companyService.retrieveCompanyDto("TK");

        assertEquals("TK",companyResponse.getCompanyCode());
        assertEquals("THY",companyResponse.getName());

    }

    @Test
    public  void retrieveCompanyTest(){

        when(companyRepository.findByCompanyCode("TK")).thenReturn(Optional.of(company));

        Company companyResponse = companyService.retrieveCompany("TK");

        assertEquals("TK",companyResponse.getCompanyCode());
        assertEquals("THY",companyResponse.getName());
    }

    @Test
    public  void addCompanyTest(){

        when(companyRepository.findByCompanyCode("TK")).thenReturn(Optional.of(company));

        when(companyDtoConverter.toDto(company)).thenReturn(companyDto);

        CompanyDto companyResponse = companyService.addCompany(new AddCompanyVo("THY","TK"));

        assertEquals("TK",companyResponse.getCompanyCode());
        assertEquals("THY",companyResponse.getName());
    }


}
