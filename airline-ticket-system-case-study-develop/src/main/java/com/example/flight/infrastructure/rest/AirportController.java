package com.example.flight.infrastructure.rest;

import com.example.flight.application.manager.AirportManager;
import com.example.flight.application.model.request.CreateAirportRequest;
import com.example.flight.application.model.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AirportController {

    private final AirportManager airportManager;

    @GetMapping("/v1/airport/{iataCode}")
    public AirportResponse retrieveAirport(@PathVariable String iataCode) {
        return airportManager.retrieveAirport(iataCode);
    }

    @PostMapping("/v1/airport")
    @ResponseStatus(HttpStatus.CREATED)
    public AirportResponse addAirport(@RequestBody CreateAirportRequest request) {
        return airportManager.addAirport(request);
    }

}
