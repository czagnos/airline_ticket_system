package com.example.flight.domain.service;


import com.example.flight.domain.model.dto.CompanyDto;
import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.dto.converter.CompanyDtoConverter;
import com.example.flight.domain.model.dto.converter.MemberDtoConverter;
import com.example.flight.domain.model.entity.Company;
import com.example.flight.domain.model.entity.Member;
import com.example.flight.domain.model.vo.AddCompanyVo;
import com.example.flight.domain.model.vo.CreateMemberVo;
import com.example.flight.domain.repository.CompanyRepository;
import com.example.flight.domain.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberDtoConverter memberDtoConverter;

    private Member member;


    private MemberDto memberDto;

    private CreateMemberVo createMemberVo;

    @Before
    public void setUp() {
        Member member = new Member();
        member.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        member.setFirstName("John");
        member.setSurname("Smith");
        member.setIdentityNumber("123456789123");

        this.member = member;

        MemberDto memberDto = new MemberDto();
        memberDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        memberDto.setFirstName("John");
        memberDto.setSurname("Smith");

        this.memberDto = memberDto;

        CreateMemberVo createMemberVo = new CreateMemberVo();
        createMemberVo.setIdentityNumber("123456789123");
        createMemberVo.setFirstName("John");
        createMemberVo.setSurname("Smith");

        this.createMemberVo = createMemberVo;

    }

    @Test
    public  void retrieveMemberByUidTest(){


        when(memberRepository.findByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae")).thenReturn(Optional.of(member));
        when(memberDtoConverter.toDto(member)).thenReturn(memberDto);


        MemberDto memberResponse = memberService.retrieveMemberDtoByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",memberResponse.getUid());
        assertEquals("John",memberResponse.getFirstName());
        assertEquals("Smith",memberResponse.getSurname());

    }

    @Test
    public  void listMemberDtoTest(){
        List<Member> memberList = new ArrayList<>();
        List<MemberDto> memberListDto = new ArrayList<>();
        memberList.add(member);
        memberList.add(member);
        memberList.add(member);
        memberListDto.add(memberDto);
        memberListDto.add(memberDto);
        memberListDto.add(memberDto);

        when(memberRepository.findAll()).thenReturn(memberList);
        when(memberDtoConverter.toDto(memberList)).thenReturn(memberListDto);

        List<MemberDto> companyResponse = memberService.listMemberDto();

        assertEquals(3,companyResponse.size());
    }

    @Test
    public  void createMemberTest(){

        when(memberRepository.findByIdentityNumber(createMemberVo.getIdentityNumber()))
                .thenReturn(Optional.of(member));
        when(memberDtoConverter.toDto(member)).thenReturn(memberDto);

        MemberDto memberResponse = memberService.createMember(createMemberVo);

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",memberResponse.getUid());
        assertEquals("John",memberResponse.getFirstName());
        assertEquals("Smith",memberResponse.getSurname());
    }


}
