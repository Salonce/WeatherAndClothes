package springTwo.example.springProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springTwo.example.springProject.dtos.City;
import springTwo.example.springProject.dtos.Location;
import springTwo.example.springProject.models.Weather;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherAPIService weatherAPIService;

    public List<Weather> getWeatherList(String cityName){
        List<Weather> weatherList = new ArrayList<>();
        List<City> cityList = weatherAPIService.requestCityList(cityName);
        for (City city : cityList){
            Location location = weatherAPIService.requestLocation(Double.toString(city.getLat()), Double.toString(city.getLon()));
            Weather weather = new Weather(city, location);
            weatherList.add(weather);
        }
        return weatherList;
    }

    public List<Weather> getWeatherList(String latitude, String longitude){
        List<Weather> cityWeatherlist = new ArrayList<>();
        Location location = weatherAPIService.requestLocation(latitude, longitude);
        Weather weather = new Weather(location);
        cityWeatherlist.add(weather);
        return cityWeatherlist;
    }
}

