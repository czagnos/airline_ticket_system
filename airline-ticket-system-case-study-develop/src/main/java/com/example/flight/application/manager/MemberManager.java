package com.example.flight.application.manager;

import com.example.flight.application.model.request.CreateMemberRequest;
import com.example.flight.application.model.request.converter.CreateMemberRequestConverter;
import com.example.flight.application.model.response.MemberResponse;
import com.example.flight.domain.model.dto.MemberDto;
import com.example.flight.domain.model.vo.CreateMemberVo;
import com.example.flight.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberManager {

    private final MemberService memberService;
    private final CreateMemberRequestConverter createMemberRequestConverter;

    public MemberResponse createMember(CreateMemberRequest request) {
        CreateMemberVo createMemberVo = createMemberRequestConverter.toVo(request);
        MemberDto member = memberService.createMember(createMemberVo);

        return MemberResponse.builder()
                .member(member)
                .build();

    }

    public MemberResponse retrieveMember(String uid) {
           MemberDto member= memberService.retrieveMemberDtoByUid(uid);
           return MemberResponse.builder()
                .member(member)
                .build();



    }

    public List<MemberResponse> listMember() {
        List<MemberDto> memberList = memberService.listMemberDto();
        List<MemberResponse> responses = new ArrayList<>() ;
        for(MemberDto member : memberList)
            responses.add(MemberResponse.builder()
                    .member(member).build());
        return responses ;
    }
}
