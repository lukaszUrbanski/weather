package com.sda.weather.application.weather;

public class BadGatewayException extends RuntimeException {
    public BadGatewayException(final String message) {
        super (message);
    }
}
