package com.example.flight.domain.exception.model.entity;


import com.example.flight.domain.model.entity.enums.MessageSeverity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.mail.Message;

@Data
@AllArgsConstructor
public class Error
{
    private String message;
    private MessageSeverity severity;

}
