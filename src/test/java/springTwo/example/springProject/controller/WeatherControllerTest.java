package springTwo.example.springProject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springTwo.example.springProject.service.weather.WeatherService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WeatherController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    void addAttributes() {
    }

    @Test
    void testGetWeatherSite() throws Exception {
        // THEN
        RequestBuilder request = MockMvcRequestBuilders.get("/weather");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("cityName", ""))
                .andExpect(model().attribute("activePage", "weather"));

    }
    @Test
    void getPostWeather() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/weather/coords?latitude=31&longitude=42");
        Object obj = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("cityName", ""))
                .andExpect(model().attribute("activePage", "weather"))
                .andExpect(model().attributeExists("cityWeatherDTOList"))
                .andReturn()
                .getModelAndView()
                .getModel()
                .get("cityWeatherDTOList");

        assertThat(obj).isInstanceOf(List.class);

    }

    @Disabled
    @Test
    void testGetPostWeather() {
    }

    @Test
    void greeting() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("http://127.0.0.1:8080/greeting");
        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }
}