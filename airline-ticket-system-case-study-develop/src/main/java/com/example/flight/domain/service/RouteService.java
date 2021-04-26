package com.example.flight.domain.service;

import com.example.flight.domain.exception.RouteNotFoundException;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.dto.converter.RouteDtoConverter;
import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.entity.Route;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteDtoConverter routeDtoConverter;
    private final AirportService airportService;

    public RouteDto retrieveRouteDto(String origin, String destination) {
        Route route = retrieveRoute(origin, destination);
        return routeDtoConverter.toDto(route);
    }

    public Route retrieveRoute(String origin, String destination) {
        return routeRepository.findByOriginIataCodeAndDestinationIataCode(origin, destination)
                .orElseThrow(RouteNotFoundException::new);
    }

    public Route retrieveRouteByUid(String routeUid) {
        return routeRepository.findByUid(routeUid)
                .orElseThrow(RouteNotFoundException::new);
    }

    public RouteDto addRoute(AddRouteVo addRouteVo) {
        String origin = addRouteVo.getOrigin();
        String destination = addRouteVo.getDestination();

        Route route = routeRepository.findByOriginIataCodeAndDestinationIataCode(origin, destination)
                .orElseGet(() -> saveRoute(origin, destination));

        return routeDtoConverter.toDto(route);
    }

    private Route saveRoute(String origin, String destination) {
        Airport originAirport = airportService.retrieveAirport(origin);
        Airport destinationAirport = airportService.retrieveAirport(destination);

        Route route = new Route();
        route.setUid(UUID.randomUUID().toString());
        route.setOrigin(originAirport);
        route.setDestination(destinationAirport);
        return routeRepository.save(route);
    }

}
