package springTwo.example.springProject.Weather;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.TokenConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private TokenConfig tokenConfig;

    @Autowired
    WeatherService(TokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    private WeatherDTO getWeatherDTO(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=" + tokenConfig.getToken();
        ResponseEntity<WeatherDTO> response = restTemplate.getForEntity(resourceUrl, WeatherDTO.class);

        return response.getBody();
    }

    private List<CityDTO> getCityDTOlist(String cityName){
        String resourceUrl = "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=5&appid=" + tokenConfig.getToken();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CityDTO>> responseEntity = restTemplate.exchange(
        resourceUrl,
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
