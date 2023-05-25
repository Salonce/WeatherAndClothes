package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.OpenWeatherMapConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    WeatherService(OpenWeatherMapConfig openWeatherMapConfig){
        this.openWeatherMapConfig = openWeatherMapConfig;
    }

    private WeatherDTO getWeatherDTO(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherDTO> response = restTemplate.getForEntity(openWeatherMapConfig.getByCoordinatesURL(latitude, longitude), WeatherDTO.class);
        return response.getBody();
    }

    private List<CityDTO> getCityDTOlist(String cityName){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CityDTO>> responseEntity = restTemplate.exchange(
            openWeatherMapConfig.getByCityURL(cityName),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CityDTO>>() {}
            );
        return responseEntity.getBody();
    }

    List<CityWeatherDTO> getCityWeatherDTOlist(String cityName){
        List<CityWeatherDTO> cityWeatherDTOlist = new ArrayList<>();
        for (CityDTO cityDTO : getCityDTOlist(cityName)){
            cityWeatherDTOlist.add(new CityWeatherDTO(cityDTO, getWeatherDTO(Double.toString(cityDTO.getLat()), Double.toString(cityDTO.getLon()))));
        }
        return cityWeatherDTOlist;
    }

    List<CityWeatherDTO> getCityWeatherDTOlist(String latitude, String longitude){
        List<CityWeatherDTO> cityWeatherDTOlist = new ArrayList<>();
        cityWeatherDTOlist.add(new CityWeatherDTO(getWeatherDTO(latitude, longitude)));
        return cityWeatherDTOlist;
    }
}
