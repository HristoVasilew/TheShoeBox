package TheShoeBox.TheShoeBox.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class WebsiteControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void showAboutWithoutAuthenticatedUserShouldReturnLoginPage() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.get("/shop/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void showNewsletterWithoutAuthenticatedUserShouldReturnLoginPage() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.get("/shop/newsletter"))
                .andExpect(status().isOk())
                .andExpect(view().name("newsletter"));
    }

    @Test
    void showConditionsWithoutAuthenticatedUserShouldReturnLoginPage() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.get("/shop/conditions"))
                .andExpect(status().isOk())
                .andExpect(view().name("conditions"));
    }
}