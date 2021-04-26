package com.example.flight.domain.repository;

import com.example.flight.domain.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findByOriginIataCodeAndDestinationIataCode(String origin, String destination);

    Optional<Route> findByUid(String uid);

}
