package com.example.flight.infrastructure.rest.exception;


import com.example.flight.application.model.response.ExceptionResponse;
import com.example.flight.domain.exception.*;
import com.example.flight.utils.ErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;



@ControllerAdvice
public class GlobalExceptionHandler {


   @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<ExceptionResponse> airportNotFound(AirportNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> companyNotFound(CompanyNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightCapacityExceedException.class)
    public ResponseEntity<ExceptionResponse> flightCapacityExceed(FlightCapacityExceedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ExceptionResponse> flightNotFound(FlightNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ExceptionResponse> memberNotFound(MemberNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<ExceptionResponse> routeNotFound(RouteNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketAlreadyBoughtException.class)
    public ResponseEntity<ExceptionResponse> ticketAlreadyBought(TicketAlreadyBoughtException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ticketNotFound(TicketNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketCancelException.class)
    public ResponseEntity<ExceptionResponse> ticketCancelNotFound(TicketCancelException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ErrorMapper.read(ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

}
