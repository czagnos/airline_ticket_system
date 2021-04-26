package com.example.flight.application.manager;

import com.example.flight.application.model.request.CreateFlightRequest;
import com.example.flight.application.model.request.converter.CreateFlightRequestConverter;
import com.example.flight.application.model.response.FlightResponse;
import com.example.flight.domain.model.dto.FlightDto;
import com.example.flight.domain.model.vo.AddFlightVo;
import com.example.flight.domain.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightManager {

    private final FlightService flightService;
    private final CreateFlightRequestConverter createFlightRequestConverter;

    public FlightResponse retrieveFlight(String flightNumber) {
        FlightDto flight = flightService.retrieveFlightDto(flightNumber);

        return FlightResponse.builder()
                .flight(flight)
                .build();
    }

    public FlightResponse addFlight(CreateFlightRequest request) {
        AddFlightVo addFlightVo = createFlightRequestConverter.toVo(request);
        FlightDto flight = flightService.addFlight(addFlightVo);

        return FlightResponse.builder()
                .flight(flight)
                .build();
    }
}
