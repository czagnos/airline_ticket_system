package com.example.flight.infrastructure.rest;

import com.example.flight.application.manager.CompanyManager;
import com.example.flight.application.model.request.CreateCompanyRequest;
import com.example.flight.application.model.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyManager companyManager;

    @GetMapping("/v1/company/{companyCode}")
    public CompanyResponse retrieveAirport(@PathVariable String companyCode) {
        return companyManager.retrieveCompany(companyCode);
    }

    @PostMapping("/v1/company")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CreateCompanyRequest request) {
        return companyManager.addCompany(request);
    }
}
