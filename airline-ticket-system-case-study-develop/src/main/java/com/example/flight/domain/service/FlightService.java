package com.example.flight.domain.service;

import com.example.flight.domain.exception.FlightCapacityExceedException;
import com.example.flight.domain.exception.FlightNotFoundException;
import com.example.flight.domain.model.dto.FlightDto;
import com.example.flight.domain.model.dto.converter.FlightDtoConverter;
import com.example.flight.domain.model.entity.Company;
import com.example.flight.domain.model.entity.Flight;
import com.example.flight.domain.model.entity.Route;
import com.example.flight.domain.model.vo.AddFlightVo;
import com.example.flight.domain.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final CompanyService companyService;
    private final RouteService routeService;
    private final FlightDtoConverter flightDtoConverter;

    public FlightDto retrieveFlightDto(String flightNumber) {
        Flight flight = retrieveFlight(flightNumber);
        return flightDtoConverter.toDto(flight);
    }

    public Flight retrieveFlight(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(FlightNotFoundException::new);
    }

    public FlightDto addFlight(AddFlightVo addFlightVo) {
        //todo already created response
        Flight flight = flightRepository.findByFlightNumber(addFlightVo.getFlightNumber())
                .orElseGet(() -> saveFlight(addFlightVo));
        return flightDtoConverter.toDto(flight);
    }

    private Flight saveFlight(AddFlightVo addFlightVo) {
        Flight flight = new Flight();
        flight.setFlightNumber(addFlightVo.getFlightNumber());
        flight.setCapacity(addFlightVo.getCapacity());
        flight.setBasePrice(addFlightVo.getBasePrice());

        Company company = companyService.retrieveCompany(addFlightVo.getCompanyCode());
        flight.setCompany(company);

        Route route = routeService.retrieveRouteByUid(addFlightVo.getRouteUid());
        flight.setRoute(route);

        return flightRepository.save(flight);
    }

    public Integer getPassengerCountForFlight(String flightNumber) {
        return (int) retrieveFlight(flightNumber).getTicket().size();
    }

    public void setAddPassengerCountForFlight(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(FlightNotFoundException::new);
        flight.setCapacity(flight.getCapacity()+1);
        flightRepository.save(flight);

    }

    public void setRemovePassengerCountForFlight(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(FlightNotFoundException::new);
        flight.setCapacity(flight.getCapacity()-1);
        flightRepository.save(flight);
    }

    public void validateFlightCapacity(Integer flightCapacity, String fightNumber) {
           if(flightRepository.findByFlightNumber(fightNumber).get().getCapacity() ==0 ){
               throw new FlightCapacityExceedException();
           };
    }
}
