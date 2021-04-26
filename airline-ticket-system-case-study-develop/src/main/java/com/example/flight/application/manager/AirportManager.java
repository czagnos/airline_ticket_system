package com.example.flight.application.manager;

import com.example.flight.application.model.request.CreateAirportRequest;
import com.example.flight.application.model.request.converter.CreateAirportRequestConverter;
import com.example.flight.application.model.response.AirportResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.vo.AddAirportVo;
import com.example.flight.domain.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportManager {

    private final AirportService airportService;
    private final CreateAirportRequestConverter createAirportRequestConverter;

    public AirportResponse retrieveAirport(String iataCode) {
        AirportDto airportDto = airportService.retrieveAirportDtoByIataCode(iataCode);
        return AirportResponse.builder()
                .airport(airportDto)
                .build();
    }

    public AirportResponse addAirport(CreateAirportRequest request) {
        //todo validate
        AddAirportVo addAirportVo = createAirportRequestConverter.toVo(request);
        AirportDto airportDto = airportService.addAirport(addAirportVo);
        return AirportResponse.builder()
                .airport(airportDto)
                .build();
    }
}
