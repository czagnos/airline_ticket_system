package com.example.flight.domain.exception.model.entity;


import com.example.flight.domain.model.entity.enums.MessageSeverity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Errors {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    private List<Error> errors;

    public Errors(String message, MessageSeverity error) {
        this.errors = Arrays.asList(new Error(message, error));
    }

}