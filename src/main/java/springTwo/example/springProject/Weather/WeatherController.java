package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {
    @ModelAttribute
    void addAttributes(Model model) {
        model.addAttribute("activePage", "weather");
    }

    private WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather")
    String getWeatherSite(Model model) {
        model.addAttribute("coordinates", new Coordinates());
        model.addAttribute("cityName", new String());
        return "weather";
    }

    @PostMapping(value = "/weather")
    String getPostWeather(@ModelAttribute("coordinates") Coordinates coordinates, Model model) {
        model.addAttribute("latitude", coordinates.getLatitude());
        model.addAttribute("long", coordinates.getLongitude());

        WeatherDTO weatherDTO = weatherService.getWeather(coordinates.getLatitude(), coordinates.getLongitude());
        model.addAttribute("temperature", weatherDTO.getMain().getTemp_max());
        model.addAttribute("pressure", weatherDTO.getMain().getPressure());
        model.addAttribute("humidity", weatherDTO.getMain().getHumidity());
        model.addAttribute("wind", weatherDTO.getWind().getSpeed());
        model.addAttribute("clouds", weatherDTO.getClouds().getAll());

        return "weather";
    }
}

/*
    @PostMapping(value="/weather")
    String getPostWeather(@ModelAttribute("cityName") String cityName, Model model){

        CityDTO cityDTO = weatherService.getCityDTO(cityName);
        Double lat = cityDTO.getLat();
        Double lon = cityDTO.getLon();
        Coordinates coordinates = new Coordinates(lat.toString(), lon.toString());
        WeatherDTO weatherDTO = weatherService.getWeather(coordinates.getLatitude(), coordinates.getLongitude());

        model.addAttribute("latitude", coordinates.getLatitude());
        model.addAttribute("long", coordinates.getLongitude());
        model.addAttribute("temperature", weatherDTO.getMain().getTemp_max());
        model.addAttribute("pressure", weatherDTO.getMain().getPressure());
        model.addAttribute("humidity", weatherDTO.getMain().getHumidity());
        model.addAttribute("wind", weatherDTO.getWind().getSpeed());
        model.addAttribute("clouds", weatherDTO.getClouds().getAll());

        return "weather";
    }
}
*/


/*
    @ResponseBody
    WeatherDTO getWeather(){
        return weatherService.getWeather();
    }
 */