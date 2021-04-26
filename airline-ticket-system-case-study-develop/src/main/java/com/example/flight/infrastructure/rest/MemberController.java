package com.example.flight.infrastructure.rest;

import com.example.flight.application.manager.MemberManager;
import com.example.flight.application.model.request.CreateMemberRequest;
import com.example.flight.application.model.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberManager memberManager;

    @PostMapping("/v1/member")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse createMember(@RequestBody CreateMemberRequest request) {
        return memberManager.createMember(request);
    }

    @GetMapping("/v1/member/{uid}")
    public MemberResponse retrieveMember(@PathVariable String uid) {
        return memberManager.retrieveMember(uid);
    }

    @GetMapping("/v1/member/list")
    public List<MemberResponse> listMember() {
        return memberManager.listMember();
    }
}
