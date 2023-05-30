package springTwo.example.springProject.service.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.config.OpenWeatherMapConfig;
import springTwo.example.springProject.dto.CityDto;
import springTwo.example.springProject.dto.WeatherDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    WeatherService(OpenWeatherMapConfig openWeatherMapConfig){
        this.openWeatherMapConfig = openWeatherMapConfig;
    }

    private WeatherDto getWeatherDTO(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherDto> response = restTemplate.getForEntity(openWeatherMapConfig.getByCoordinatesURL(latitude, longitude), WeatherDto.class);
        return response.getBody();
    }

    private List<CityDto> getCityDTOlist(String cityName){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CityDto>> responseEntity = restTemplate.exchange(
            openWeatherMapConfig.getByCityURL(cityName),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CityDto>>() {}
            );
        return responseEntity.getBody();
    }

    public List<CityWeather> getCityWeatherDTOlist(String cityName){
        List<CityWeather> cityWeatherDTOlist = new ArrayList<>();
        for (CityDto cityDTO : getCityDTOlist(cityName)){
            cityWeatherDTOlist.add(new CityWeather(cityDTO, getWeatherDTO(Double.toString(cityDTO.getLat()), Double.toString(cityDTO.getLon()))));
        }
        return cityWeatherDTOlist;
    }

    public List<CityWeather> getCityWeatherDTOlist(String latitude, String longitude){
        List<CityWeather> cityWeatherlist = new ArrayList<>();
        cityWeatherlist.add(new CityWeather(getWeatherDTO(latitude, longitude)));
        return cityWeatherlist;
    }
}

