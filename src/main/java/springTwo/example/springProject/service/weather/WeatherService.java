package springTwo.example.springProject.service.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.config.OpenWeatherMapConfig;
import springTwo.example.springProject.dto.City;
import springTwo.example.springProject.dto.Location;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    WeatherService(OpenWeatherMapConfig openWeatherMapConfig){
        this.openWeatherMapConfig = openWeatherMapConfig;
    }

    private Location getLocation(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Location> response = restTemplate.getForEntity(openWeatherMapConfig.getByCoordinatesURL(latitude, longitude), Location.class);
        return response.getBody();
    }

    private List<City> getCityList(String cityName){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<City>> responseEntity = restTemplate.exchange(
            openWeatherMapConfig.getByCityURL(cityName),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<City>>() {}
            );
        return responseEntity.getBody();
    }

    public List<Weather> getWeatherList(String cityName){
        List<Weather> weatherList = new ArrayList<>();
        for (City city : getCityList(cityName)){
            Weather weather = new Weather(city, getLocation(Double.toString(city.getLat()), Double.toString(city.getLon())));
            weatherList.add(weather);
        }
        return weatherList;
    }

    public List<Weather> getWeatherList(String latitude, String longitude){
        List<Weather> cityWeatherlist = new ArrayList<>();
        Weather weather = new Weather(getLocation(latitude, longitude));
        cityWeatherlist.add(weather);
        return cityWeatherlist;
    }
}

