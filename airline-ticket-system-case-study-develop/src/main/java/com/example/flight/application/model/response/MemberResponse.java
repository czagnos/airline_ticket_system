package com.example.flight.application.model.response;

import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.dto.TicketDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberResponse {

    @JsonUnwrapped
    MemberDto member;

}
