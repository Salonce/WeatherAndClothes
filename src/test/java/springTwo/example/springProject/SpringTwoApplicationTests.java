package springTwo.example.springProject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springTwo.example.springProject.controllers.WardrobeController;
import springTwo.example.springProject.controllers.WeatherController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringTwoApplicationTests {

	private WardrobeController wardrobeController;
	private WeatherController weatherController;

	@Autowired
	SpringTwoApplicationTests(WardrobeController wardrobeController, WeatherController weatherController){
		this.wardrobeController = wardrobeController;
		this.weatherController = weatherController;
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(wardrobeController).isNotNull();
		assertThat(weatherController).isNotNull();
	}

}
