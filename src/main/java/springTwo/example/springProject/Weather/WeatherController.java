package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WeatherController {

    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("coordinates", new Coordinates());
        model.addAttribute("cityName", new String());
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

    @PostMapping(value="/weather", params = "latitude")
    String getPostWeather(@ModelAttribute("coordinates") Coordinates coordinates, Model model){
        List<CityWeatherDTO> cityWeatherDTOList = weatherService.getCityWeatherDTOlist(coordinates.getLatitude(), coordinates.getLongitude());
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }


    @PostMapping(value="/weather", params = "cityName")
    String getPostWeather(@RequestParam("cityName") String cityName, Model model) {
        List<CityWeatherDTO> cityWeatherDTOList = weatherService.getCityWeatherDTOlist(cityName);
        model.addAttribute("cityWeatherDTOList", cityWeatherDTOList);
        return "weather";
    }
}
