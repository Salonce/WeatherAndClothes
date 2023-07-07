package springTwo.example.springProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springTwo.example.springProject.models.Weather;
import springTwo.example.springProject.services.WeatherService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("cityName", new String());
        model.addAttribute("activePage", "weather");
    }

    @GetMapping(value="/weather")
    public String getWeatherSite(Model model){
        return "weather";
    }

    @GetMapping(value="/weather/coords")
    public String getWeatherByCoords(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,  Model model){
        List<Weather> cityWeatherDTOList = weatherService.getWeatherList(latitude, longitude);
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }

    @GetMapping(value="/weather/city")
    public String getWeatherByCityName(@RequestParam("cityName") String cityName, Model model) {
        List<Weather> cityWeatherDTOList = weatherService.getWeatherList(cityName);
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }
}
