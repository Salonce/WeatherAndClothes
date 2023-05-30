package springTwo.example.springProject.service.weather;

import springTwo.example.springProject.dto.CityDto;
import springTwo.example.springProject.dto.WeatherDto;

public class CityWeather {
    public CityWeather(WeatherDto weatherDTO) {
        this.cityDTO = null;
        this.weatherDTO = weatherDTO;
    }

    public CityWeather(CityDto cityDTO, WeatherDto weatherDTO) {
        this.cityDTO = cityDTO;
        this.weatherDTO = weatherDTO;
    }

    private CityDto cityDTO;
    private WeatherDto weatherDTO;

    public CityDto getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDto cityDTO) {
        this.cityDTO = cityDTO;
    }

    public WeatherDto getWeatherDTO() {
        return weatherDTO;
    }

    public void setWeatherDTO(WeatherDto weatherDTO) {
        this.weatherDTO = weatherDTO;
    }
}
