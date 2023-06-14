package springTwo.example.springProject.models;

import springTwo.example.springProject.dtos.City;
import springTwo.example.springProject.dtos.Location;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
