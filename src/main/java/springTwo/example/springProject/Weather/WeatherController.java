package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springTwo.example.springProject.Item.Item;

@Controller
public class WeatherController {
    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("activePage", "weather");
    }

    private WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(value="/weather")
    String getWeatherSite(Model model){
        model.addAttribute("coordinates", new Coordinates());
        return "weather";
    }

    //@GetMapping(value="/weatherr", consumes="application/json")
    @PostMapping(value="/weather")
    String getPostWeather(@ModelAttribute("coordinates") Coordinates coordinates, Model model){
        model.addAttribute("lat", coordinates.getLatitude());
        model.addAttribute("long", coordinates.getLongitude());
        WeatherDTO weatherDTO = weatherService.getWeather(coordinates.getLatitude(), coordinates.getLongitude());
        //model.addAttribute("minTemperature", weatherDTO.getMain().getTemp_min());
        //model.addAttribute("maxTemperature", weatherDTO.getMain().getTemp());
        model.addAttribute("temperature", weatherDTO.getMain().getTemp_max());
        model.addAttribute("pressure", weatherDTO.getMain().getPressure());
        model.addAttribute("humidity", weatherDTO.getMain().getHumidity());
        model.addAttribute("wind", weatherDTO.getWind().getSpeed());
        model.addAttribute("clouds", weatherDTO.getClouds().getAll());

        return "weather";
    }
}

/*
    @ResponseBody
    WeatherDTO getWeather(){
        return weatherService.getWeather();
    }
 */