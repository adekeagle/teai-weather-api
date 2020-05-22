package pl.adcom.teaiweatherapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.adcom.teaiweatherapi.model.Weather;
import pl.adcom.teaiweatherapi.service.WeatherService;

@Controller
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/app")
    public String getId(Model model, @RequestParam(value = "cityName", required = false, defaultValue = "Warsaw") String cityName){

        Weather warsaw = weatherService.getWeatherByCity(cityName);

        if(warsaw != null) {
            model.addAttribute("weather", warsaw.getMain());
            model.addAttribute("icon", warsaw.getSrc());
            model.addAttribute("costam", warsaw.getName());
        }else{
            return "error";
        }
        return "weather";
    }
}
