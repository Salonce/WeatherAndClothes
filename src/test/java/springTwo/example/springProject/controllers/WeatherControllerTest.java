package springTwo.example.springProject.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import springTwo.example.springProject.config.SecurityConfig;
import springTwo.example.springProject.services.WeatherService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WeatherController.class)
@Import(SecurityConfig.class)
public class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetWeatherSite() throws Exception {
        // GIVEN
        RequestBuilder request = get("/weather");

        // WHEN THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("cityName", ""))
                .andExpect(model().attribute("activePage", "weather"));

    }
    @Test
    void getPostWeather() throws Exception {
        // GIVEN
        RequestBuilder request = get("/weather/coords?latitude=31&longitude=42");

        // WHEN THEN
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
    void addAttributes() {

    }

    @Disabled
    @Test
    void getWeatherSite() {
    }

    @Disabled
    @Test
    void testGetPostWeather() {
    }

    @Disabled
    @Test
    void testGetPostWeather1() {
    }
}