package com.example.flight.application.manager;


import com.example.flight.application.model.request.CreateMemberRequest;
import com.example.flight.application.model.request.CreateRouteRequest;
import com.example.flight.application.model.request.converter.CreateMemberRequestConverter;
import com.example.flight.application.model.request.converter.CreateRouteRequestConverter;
import com.example.flight.application.model.response.MemberResponse;
import com.example.flight.application.model.response.RouteResponse;
import com.example.flight.domain.model.dto.AirportDto;
import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.dto.RouteDto;
import com.example.flight.domain.model.entity.Member;
import com.example.flight.domain.model.vo.AddRouteVo;
import com.example.flight.domain.model.vo.CreateMemberVo;
import com.example.flight.domain.service.MemberService;
import com.example.flight.domain.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MemberManagerTest {

    @InjectMocks
    MemberManager memberManager;

    @Mock
    MemberService memberService;

    @Mock
    CreateMemberRequestConverter createMemberRequestConverter;


    @Test
    public  void retrieveMemberByUidTest(){
        MemberDto memberDto = new MemberDto();
        memberDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        memberDto.setFirstName("John");
        memberDto.setSurname("Smith");

        when(memberService.retrieveMemberDtoByUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae"))
                .thenReturn(memberDto);

        MemberResponse memberResponse = memberManager.retrieveMember("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",memberResponse.getMember().getUid());
        assertEquals("John",memberResponse.getMember().getFirstName());
        assertEquals("Smith",memberResponse.getMember().getSurname());


    }

    @Test
    public  void createMemberTest(){
        MemberDto memberDto = new MemberDto();
        memberDto.setUid("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae");
        memberDto.setFirstName("John");
        memberDto.setSurname("Smith");

        CreateMemberVo createMemberVo = new CreateMemberVo();
        createMemberVo.setFirstName("John");
        createMemberVo.setSurname("Smith");
        createMemberVo.setIdentityNumber("123456789123");

        when(createMemberRequestConverter
                .toVo(new CreateMemberRequest("John","Smith","123456789132")))
                .thenReturn(createMemberVo);
        when(memberService.createMember(createMemberVo)).thenReturn(memberDto);

        MemberResponse memberResponse = memberManager
                .createMember(new CreateMemberRequest("John","Smith","123456789132"));

        assertEquals("5638a6f2-4de8-4d1c-9518-a7c3f8b0daae",memberResponse.getMember().getUid());
        assertEquals("John",memberResponse.getMember().getFirstName());
        assertEquals("Smith",memberResponse.getMember().getSurname());
        verify(memberService, times(1)).createMember(createMemberVo );
    }

    @Test
    public  void retrieveMemberListTest(){
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>();
        MemberDto memberDto1 = new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daa1","John","Smith");
        MemberDto memberDto2 = new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daa2","Sam","Black");
        MemberDto memberDto3 = new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daa3","Jack","Pink");
        MemberDto memberDto4 = new MemberDto("5638a6f2-4de8-4d1c-9518-a7c3f8b0daa4","Carl","Orange");
        memberDtoList.add(memberDto1);
        memberDtoList.add(memberDto2);
        memberDtoList.add(memberDto3);
        memberDtoList.add(memberDto4);

        when(memberService.listMemberDto())
                .thenReturn(memberDtoList);

        List<MemberResponse> memberResponse = memberManager.listMember();

        assertEquals(4, memberResponse.size());

    }


}
