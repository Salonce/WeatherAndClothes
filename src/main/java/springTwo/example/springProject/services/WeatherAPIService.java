package springTwo.example.springProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.config.OpenWeatherMapConfig;
import springTwo.example.springProject.dtos.City;
import springTwo.example.springProject.dtos.Location;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherAPIService {

    private final OpenWeatherMapConfig openWeatherMapConfig;

    public Location requestLocation(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Location> response = restTemplate.getForEntity(openWeatherMapConfig.getByCoordinatesURL(latitude, longitude), Location.class);
        return response.getBody();
    }

    public List<City> requestCityList(String cityName){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<City>> responseEntity = restTemplate.exchange(
                openWeatherMapConfig.getByCityURL(cityName),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<City>>() {}
        );
        return responseEntity.getBody();
    }
}
