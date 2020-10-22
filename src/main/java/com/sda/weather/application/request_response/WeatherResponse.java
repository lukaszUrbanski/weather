package com.sda.weather.application.request_response;

import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationRepository;
import com.sda.weather.application.weather.Weather;
import com.sda.weather.application.weather.WeatherRepository;
import lombok.Data;

@Data
public class WeatherResponse {

//    String name;
//    String country;
//    String Region;
//    String lat;
//    String lon;
//    String localtime;
//    String utc_offset;

    private Current current;

    public Weather getWeather(final String cityName){
        LocationRepository locationRepository = new LocationRepository();
        return new Weather(
                current.getTemperature(),
                current.getPressure(),
                current.getHumidity(),
                current.getWind_dir(),
                current.getWind_speed(),
                locationRepository.getLocation(cityName));
    };  // w resources/example_response.json wkleiłem strukturę otrzymanego JSON'a - są tam zagnieżdżone obiekty
}
