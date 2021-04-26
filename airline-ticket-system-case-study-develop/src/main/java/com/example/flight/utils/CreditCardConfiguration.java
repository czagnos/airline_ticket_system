package com.example.flight.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.UUID;

public class CreditCardConfiguration {

    private CreditCardConfiguration() {
    }

    public static String fixCreditCardNumber(String creditCardNumber) {
        return creditCardNumber.replaceAll("[^0-9]+","");
    }

    public static String maskCard(String creditCardNumber) {
        return maskString(creditCardNumber,6,12,'*');
    }


    private static String maskString(String strText, int start, int end, char maskChar) {

        if(strText == null || strText.equals(""))
            return "";

        if(start < 0)
            start = 0;

        if( end > strText.length() )
            end = strText.length();


        int maskLength = end - start;

        if(maskLength == 0)
            return strText;

        String strMaskString = StringUtils.repeat(maskChar, maskLength);

        return StringUtils.overlay(strText, strMaskString, start, end);
    }
}
