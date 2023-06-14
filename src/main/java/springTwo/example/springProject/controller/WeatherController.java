package springTwo.example.springProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springTwo.example.springProject.service.weather.Weather;
import springTwo.example.springProject.service.weather.WeatherService;

import java.util.List;

@Controller
public class WeatherController {

    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("cityName", new String());
        model.addAttribute("activePage", "weather");
    }

    private WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(value="/weather")
    String getWeatherSite(Model model){
        return "weather";
    }

    @GetMapping(value="/weather/coords")
    String getPostWeather(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,  Model model){
        List<Weather> cityWeatherDTOList = weatherService.getWeatherList(latitude, longitude);
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }

    @GetMapping(value="/weather/city")
    String getPostWeather(@RequestParam("cityName") String cityName, Model model) {
        List<Weather> cityWeatherDTOList = weatherService.getWeatherList(cityName);
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return "dasd";
    }
}
