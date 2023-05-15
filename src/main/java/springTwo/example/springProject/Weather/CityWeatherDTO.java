package springTwo.example.springProject.Weather;

public class CityWeatherDTO {
    CityWeatherDTO(WeatherDTO weatherDTO){
        this.cityDTO = null;
        this.weatherDTO = weatherDTO;
    }

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
