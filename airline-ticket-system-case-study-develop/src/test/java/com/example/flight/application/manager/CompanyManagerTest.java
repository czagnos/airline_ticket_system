package com.example.flight.application.manager;


import com.example.flight.application.model.request.CreateAirportRequest;
import com.example.flight.application.model.request.CreateCompanyRequest;
import com.example.flight.application.model.request.converter.CreateAirportRequestConverter;
import com.example.flight.application.model.request.converter.CreateCompanyRequestConverter;
import com.example.flight.application.model.response.AirportResponse;
import com.example.flight.application.model.response.CompanyResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.service.AirportService;
import com.example.flight.domain.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CompanyManagerTest {

    @InjectMocks
    CompanyManager companyManager;

    @Mock
    CompanyService companyService;

    @Mock
    CreateCompanyRequestConverter createCompanyRequestConverter;


    @Test
    public  void retrieveCompanyByCompanyCodeTest(){
        CompanyDto companyDto = new CompanyDto("Turk Hava Yolları","TK");
        when(companyService.retrieveCompanyDto("TK"))
                .thenReturn(companyDto);
        CompanyResponse companyResponse = companyManager.retrieveCompany("TK");

        assertEquals("TK",companyResponse.getCompany().getCompanyCode());
        assertEquals("Turk Hava Yolları",companyResponse.getCompany().getName());

    }

    @Test
    public  void addCompanyTest(){
        CompanyDto airportDto = new CompanyDto("Turk Hava Yolları","TK");
        AddCompanyVo addCompanyVo = new AddCompanyVo("Turk Hava Yolları","TK");
        when(createCompanyRequestConverter.toVo(new CreateCompanyRequest("Turk Hava Yolları","TK")))
                .thenReturn(addCompanyVo);
        when(companyService.addCompany(addCompanyVo)).thenReturn(airportDto);

        CompanyResponse airportResponse = companyManager.addCompany(new CreateCompanyRequest("Turk Hava Yolları","TK"));

        assertEquals("TK",airportResponse.getCompany().getCompanyCode());
        assertEquals("Turk Hava Yolları",airportResponse.getCompany().getName());
        verify(companyService, times(1)).addCompany(addCompanyVo);
    }


}
