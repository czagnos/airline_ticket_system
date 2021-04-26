package com.example.flight.infrastructure.rest;

import com.example.flight.application.manager.RouteManager;
import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.response.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RouteController {

    private final RouteManager routeManager;

    @GetMapping("/v1/route")
    public RouteResponse retrieveRoute(@RequestParam String origin, @RequestParam String destination) {
        return routeManager.retrieveRoute(origin, destination);
    }

    @PostMapping("/v1/route")
    @ResponseStatus(HttpStatus.CREATED)
    public RouteResponse addRoute(@RequestBody CreateRouteRequest request) {
        return routeManager.addRoute(request);
    }
}
