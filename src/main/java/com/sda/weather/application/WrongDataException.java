package com.sda.weather.application;

public class WrongDataException extends RuntimeException{
    public WrongDataException(final String message) {
        super(message);
    }
}
