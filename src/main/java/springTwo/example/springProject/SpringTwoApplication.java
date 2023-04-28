package springTwo.example.springProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@SpringBootApplication
public class SpringTwoApplication {
	public static void main(String[] args) throws IOException {

		SpringApplication.run(SpringTwoApplication.class, args);
	}
}

@Configuration
@ConfigurationProperties(prefix = "weather-api-token")
class ConfigProperties {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
