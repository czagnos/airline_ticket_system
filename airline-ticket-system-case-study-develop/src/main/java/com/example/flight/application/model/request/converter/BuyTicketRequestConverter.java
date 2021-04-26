package com.example.flight.application.model.request.converter;

import com.example.flight.application.model.request.BuyTicketRequest;
import com.example.flight.domain.model.vo.BuyTicketVo;
import org.mapstruct.Mapper;

@Mapper
public interface BuyTicketRequestConverter {

    BuyTicketVo toVo(BuyTicketRequest request);
}
