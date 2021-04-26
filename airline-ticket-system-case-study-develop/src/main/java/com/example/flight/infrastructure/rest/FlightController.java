package com.example.flight.infrastructure.rest;

import com.example.flight.application.manager.FlightManager;
import com.example.flight.application.model.request.CreateFlightRequest;
import com.example.flight.application.model.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightManager flightManager;
    @GetMapping("/v1/flight/{flightNumber}")
    public FlightResponse retrieveFlight(@PathVariable String flightNumber) {
        return flightManager.retrieveFlight(flightNumber);
    }

    @PostMapping("/v1/flight")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightResponse addFlight(@RequestBody CreateFlightRequest request) {
        return flightManager.addFlight(request);
    }
}
