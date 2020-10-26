package com.sda.weather.application.location.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationResponse {

    @JsonProperty("name")
    private String city;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lon")
    private Double longitude;
    private String region;
    private String country;

}
