package com.example.flight.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberVo {

    private String firstName;
    private String surname;
    private String identityNumber;

}
