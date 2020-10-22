package com.sda.weather.application.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseLocation {
    /*
    name": "London",
    "country": "United Kingdom",
    "region": "City of London, Greater London",
    "lat": "51.517",
    "lon": "-0.106",
     */
    @JsonProperty("name")
    private String city;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lon")
    private Double longitude;
    private String region;
    private String country;

}
