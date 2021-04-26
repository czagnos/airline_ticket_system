package com.example.flight.domain.model.dto.converter;

import com.example.flight.domain.model.dto.TicketDto;
import com.example.flight.domain.model.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper
public interface TicketDtoConverter {

    TicketDto toDto(Ticket ticket);
}
