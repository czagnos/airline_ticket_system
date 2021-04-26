package com.example.flight.domain.exception;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super("service.exception.member.not.found");
    }
}
