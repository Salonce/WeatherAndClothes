package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        WeatherDTO weatherDTO = weatherService.getWeather(coordinates.getLatitude(), coordinates.getLongitude());
        List<WeatherDTO> weatherDTOList = new ArrayList<>();
        weatherDTOList.add(weatherDTO);
        model.addAttribute("weath", weatherDTO);
        model.addAttribute("weatherDTOList", weatherDTOList);
        return "weather";
    }


    @PostMapping(value="/weather", params = "cityName")
    String getPostWeather(@RequestParam("cityName") String cityName, Model model) {

        List<CityWeatherDTO> cityWeatherDTOList = weatherService.getCityWeatherDTOlist(weatherService.getCityDTOlist(cityName));

        //model.addAttribute("weath", weatherDTOList);


        //model.addAttribute("latitude", coordinates.getLatitude());
        //model.addAttribute("long", coordinates.getLongitude());
        //model.addAttribute("temperature", weatherDTO.getMain().getTemp_max());
        //model.addAttribute("pressure", weatherDTO.getMain().getPressure());
        //model.addAttribute("humidity", weatherDTO.getMain().getHumidity());
        //model.addAttribute("wind", weatherDTO.getWind().getSpeed());
        //model.addAttribute("clouds", weatherDTO.getClouds().getAll());

        return "weather";
    }
}

/*
    @ResponseBody
    WeatherDTO getWeather(){
        return weatherService.getWeather();
    }




        //model.addAttribute("temperature", weatherDTO.getMain().getTemp_max());
        //model.addAttribute("pressure", weatherDTO.getMain().getPressure());
        //model.addAttribute("humidity", weatherDTO.getMain().getHumidity());
        //model.addAttribute("wind", weatherDTO.getWind().getSpeed());
        //model.addAttribute("clouds", weatherDTO.getClouds().getAll());
 */

