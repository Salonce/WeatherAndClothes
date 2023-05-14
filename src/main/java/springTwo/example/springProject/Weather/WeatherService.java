package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.TokenConfig;

@Service
public class WeatherService {

    private TokenConfig tokenConfig;

    @Autowired
    WeatherService(TokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    WeatherDTO getWeather(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=" + tokenConfig.getToken();
        ResponseEntity<WeatherDTO> response = restTemplate.getForEntity(fooResourceUrl, WeatherDTO.class);

        WeatherDTO wr = response.getBody();
        System.out.println(wr.getMain().getTemp());
        return wr;
    }


}
