package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.TokenConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private TokenConfig tokenConfig;

    @Autowired
    WeatherService(TokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    WeatherDTO getWeather(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=" + tokenConfig.getToken();
        ResponseEntity<WeatherDTO> response = restTemplate.getForEntity(resourceUrl, WeatherDTO.class);

        WeatherDTO weatherDTO = response.getBody();
        System.out.println(weatherDTO.getMain().getTemp());
        return weatherDTO;
    }

    List<CityDTO> getCityDTOlist(String city){
        String resourceUrl = "http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=" + tokenConfig.getToken();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CityDTO>> responseEntity = restTemplate.exchange(
        resourceUrl,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<CityDTO>>() {}
        );
        List<CityDTO> listCityDTO = responseEntity.getBody();
    //return users.stream()
    // .map(User::getName)
    // .collect(Collectors.toList());
        return listCityDTO;
    }

    WeatherDTO getWeatherDTO(CityDTO cityDTO){
        return getWeather(Double.toString(cityDTO.getLat()), Double.toString(cityDTO.getLon()));
    }

    List<CityWeatherDTO> getCityWeatherDTOlist(List<CityDTO> cityDTOList){
        List<CityWeatherDTO> cityWeatherDTOlist = new ArrayList<>();
        for (CityDTO city : cityDTOList){
            cityWeatherDTOlist.add(new CityWeatherDTO(city, getWeatherDTO(city)));
        }
        return cityWeatherDTOlist;
    }
}
