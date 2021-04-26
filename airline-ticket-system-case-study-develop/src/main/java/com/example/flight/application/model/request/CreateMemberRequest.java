package com.example.flight.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest {

    private String firstName;
    private String surname;
    private String identityNumber;

}
