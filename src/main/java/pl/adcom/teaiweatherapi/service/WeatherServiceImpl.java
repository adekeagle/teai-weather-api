package pl.adcom.teaiweatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.adcom.teaiweatherapi.model.Weather;

import java.util.Objects;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Weather getWeatherByCity(String cityName) {

        ResponseEntity<Weather> weatherInfo = restTemplate.exchange("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apikey + "&units=metric"
                                                                    , HttpMethod.GET
                                                                    , HttpEntity.EMPTY
                                                                    , Weather.class);

        Objects.requireNonNull(weatherInfo.getBody()).setSrc("http://openweathermap.org/img/wn/" + weatherInfo.getBody().getWeather().get(0).getIcon() + "@2x.png");

        if (weatherInfo.getStatusCode() == HttpStatus.OK){
            return weatherInfo.getBody();
        }else{
            return null;
        }
    }
}
