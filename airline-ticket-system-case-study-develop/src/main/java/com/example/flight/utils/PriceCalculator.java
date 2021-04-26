package com.example.flight.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;


public class PriceCalculator {


    private PriceCalculator() {
    }

    public static BigDecimal calculatePrice(Integer flightCapacity , Integer passengerCount, BigDecimal basePrice) {

        double tempPassengerPercentage =(double) flightCapacity/10 ;
        BigDecimal currentPrice = new BigDecimal(String.valueOf(basePrice)) ;
        while(tempPassengerPercentage<(double)passengerCount){
            BigDecimal percentValue = currentPrice.movePointLeft(1);
            currentPrice = currentPrice.add(percentValue);
            tempPassengerPercentage += (double) flightCapacity/10;
        }

        return currentPrice;
    }

}
