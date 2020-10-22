package com.sda.weather.application;

public class BadGatewayException extends RuntimeException {
    public BadGatewayException(final String message) {
        super (message);
    }
}
