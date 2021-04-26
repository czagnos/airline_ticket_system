package com.example.flight.application.model.response;

import com.example.flight.domain.model.dto.AirportDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportResponse {

    @JsonUnwrapped
    AirportDto airport;
}
