package pl.adcom.teaiweatherapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import pl.adcom.teaiweatherapi.model.Weather;

public interface WeatherService {

    Weather getWeatherByCity(String cityName);
}
