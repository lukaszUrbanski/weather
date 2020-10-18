package com.sda.weather;

import com.sda.weather.client.Client;

public class WeatherApplication {
    public static void main(String[] args) {
        Client client = new Client();
        client.runClientInterface();
    }
}
