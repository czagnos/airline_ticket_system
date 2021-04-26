package com.example.flight.application.manager;

import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.request.converter.CreateRouteRequestConverter;
import com.example.flight.application.model.response.RouteResponse;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteManager {

    private final RouteService routeService;
    private final CreateRouteRequestConverter createRouteRequestConverter;


    public RouteResponse retrieveRoute(String origin, String destination) {
        RouteDto route = routeService.retrieveRouteDto(origin, destination);

        return RouteResponse.builder()
                .route(route)
                .build();
    }

    public RouteResponse addRoute(CreateRouteRequest request) {
        AddRouteVo addRouteVo = createRouteRequestConverter.toVo(request);
        RouteDto route = routeService.addRoute(addRouteVo);

        return RouteResponse.builder()
                .route(route)
                .build();
    }
}
