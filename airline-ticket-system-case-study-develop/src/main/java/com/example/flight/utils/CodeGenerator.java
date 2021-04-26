package com.example.flight.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;
import java.util.UUID;

public class CodeGenerator {

    private CodeGenerator() {
    }

    public static String generatePnrCode() {
        //todo unique
        return RandomStringUtils.randomAlphanumeric(6).toUpperCase(Locale.ENGLISH);
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
