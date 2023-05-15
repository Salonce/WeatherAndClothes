package springTwo.example.springProject.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springTwo.example.springProject.TokenConfig;

public class CityWeatherDTO {
    CityWeatherDTO(CityDTO cityDTO, WeatherDTO weatherDTO){
        this.cityDTO = cityDTO;
        this.weatherDTO = weatherDTO;
    }

    private CityDTO cityDTO;
    private WeatherDTO weatherDTO;

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    public WeatherDTO getWeatherDTO() {
        return weatherDTO;
    }

    public void setWeatherDTO(WeatherDTO weatherDTO) {
        this.weatherDTO = weatherDTO;
    }
}
