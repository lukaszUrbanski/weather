package com.sda.weather.application.location.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewLocation {

    @JsonProperty("location")
    private LocationResponse locationResponse;
}
