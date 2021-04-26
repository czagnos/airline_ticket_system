package com.example.flight.domain.service;

import com.example.flight.domain.exception.MemberNotFoundException;
import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.dto.converter.MemberDtoConverter;
import com.example.flight.domain.model.entity.Member;
import com.example.flight.domain.model.vo.CreateMemberVo;
import com.example.flight.domain.repository.MemberRepository;
import com.example.flight.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDtoConverter memberDtoConverter;

    public MemberDto retrieveMemberDtoByUid(String uid) {
        Member member = retrieveMemberByUid(uid);
        return memberDtoConverter.toDto(member);
    }

    public Member retrieveMemberByUid(String uid) {
        return memberRepository.findByUid(uid)
                .orElseThrow(MemberNotFoundException::new);
    }

    public MemberDto createMember(CreateMemberVo createMemberVo) {
        Member member = memberRepository.findByIdentityNumber(createMemberVo.getIdentityNumber())
                .orElseGet(() -> {
                    Member newMember = new Member();
                    newMember.setUid(CodeGenerator.generateUuid());
                    newMember.setFirstName(createMemberVo.getFirstName());
                    newMember.setSurname(createMemberVo.getSurname());
                    newMember.setIdentityNumber(createMemberVo.getIdentityNumber());
                    return memberRepository.save(newMember);
                });

        return memberDtoConverter.toDto(member);
    }

    public List<MemberDto> listMemberDto() {
        List<Member> member = memberRepository.findAll();
        return memberDtoConverter.toDto(member);
    }
}
