package springTwo.example.springProject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springTwo.example.springProject.Item.ItemController;
import springTwo.example.springProject.Weather.WeatherController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringTwoApplicationTests {

	private ItemController itemController;
	private WeatherController weatherController;

	@Autowired
	SpringTwoApplicationTests(ItemController itemController, WeatherController weatherController){
		this.itemController = itemController;
		this.weatherController = weatherController;
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(itemController).isNotNull();
		assertThat(weatherController).isNotNull();
	}

}
