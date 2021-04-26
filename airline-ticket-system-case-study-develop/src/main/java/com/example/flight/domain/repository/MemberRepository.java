package com.example.flight.domain.repository;


import com.example.flight.domain.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUid(String uid);

    Optional<Member> findByIdentityNumber(String identityNumber);

}
