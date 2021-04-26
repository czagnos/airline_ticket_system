package com.example.flight.domain.repository;

import com.example.flight.domain.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyCode(String companyCode);

}
