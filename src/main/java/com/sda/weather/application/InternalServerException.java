package com.sda.weather.application;

public class InternalServerException extends RuntimeException {
    public InternalServerException(final String message) {
        super(message);
    }
}
