package springTwo.example.springProject.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springTwo.example.springProject.config.SecurityConfig;
import springTwo.example.springProject.entities.Item;
import springTwo.example.springProject.services.AuthenticationService;
import springTwo.example.springProject.services.ItemService;


import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WardrobeController.class)
@Import(SecurityConfig.class)
class WardrobeControllerTest {

    @MockBean
    private ItemService itemService;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private MockMvc mockMvc;


    @Disabled
    @Test
    void addAttributes() throws Exception {
    }

    @Test
    void getWardrobeUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wardrobe").with(anonymous()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void getWardrobe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wardrobe").with(user("user")))
                    .andExpect(status().isOk())
                    .andExpect(view().name("wardrobe"))
                    .andExpect(model().attribute("activePage", "wardrobe"));
    }

    @Test
    void addItemForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wardrobe/add").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("itemAdd"))
                .andExpect(model().attributeExists("item"));
    }

    @Test
    void addItem() throws Exception {
        //GIVEN
        Item item1 = new Item();
        item1.setName("Cloth");
        item1.setWeight(BigDecimal.TEN);

        //WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/wardrobe").with(oidcLogin()).with(csrf()).flashAttr("item", item1))
                .andExpect(status().isOk())
                .andExpect(view().name("wardrobe"));


        //GIVEN
        Item item2 = new Item();
        //WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/wardrobe").with(oidcLogin()).with(csrf()).flashAttr("item", item2))
                .andExpect(status().isOk())
                .andExpect(view().name("itemAdd"))
                .andExpect(model().hasErrors());
    }

    @Disabled
    @Test
    void updateItemForm() {
    }

    @Disabled
    @Test
    void updateItem() {
    }

    @Disabled
    @Test
    void deleteItem() {
    }
}