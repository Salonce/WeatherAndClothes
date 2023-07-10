package Salonce.WeatherWardrobe.models;

import lombok.Data;
import Salonce.WeatherWardrobe.dtos.City;
import Salonce.WeatherWardrobe.dtos.Location;

@Data
public class Weather {
    public Weather(Location location) {
        this.city = null;
        this.location = location;
    }

    public Weather(City city, Location location) {
        this.city = city;
        this.location = location;
    }

    private City city;
    private Location location;
}
