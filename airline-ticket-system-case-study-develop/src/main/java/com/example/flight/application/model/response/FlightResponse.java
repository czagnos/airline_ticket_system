package com.example.flight.application.model.response;

import com.example.flight.domain.model.dto.FlightDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightResponse {

    @JsonUnwrapped
    FlightDto flight;
}
