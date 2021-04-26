package com.example.flight.utils;

import java.util.ResourceBundle;

public class ErrorMapper {
    private  static ResourceBundle bundle = ResourceBundle.getBundle("errorMessages");

    public static String read(String errorCode){
        return bundle.getString(errorCode);
    }

}
