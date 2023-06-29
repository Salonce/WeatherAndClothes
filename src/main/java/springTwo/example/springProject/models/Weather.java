package springTwo.example.springProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import springTwo.example.springProject.dtos.City;
import springTwo.example.springProject.dtos.Location;

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
