package com.sda.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main0 {

    public static void main(String[] args) {
        LocalDate weatherDate;
        try {
            weatherDate = LocalDate.parse("2020-21-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e){
            weatherDate = LocalDate.now().plusDays(1);
        }
        System.out.println(weatherDate);
    }
}
