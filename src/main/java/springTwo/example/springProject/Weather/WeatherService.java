package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather-api-token.token}")
    private String token;

    WeatherDTO getWeather(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=" + token;
        ResponseEntity<WeatherDTO> response = restTemplate.getForEntity(fooResourceUrl, WeatherDTO.class);

        WeatherDTO wr = response.getBody();
        System.out.println(wr.getMain().getTemp());
        return wr;
    }

}
