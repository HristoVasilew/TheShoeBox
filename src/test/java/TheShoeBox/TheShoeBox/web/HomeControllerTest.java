package TheShoeBox.TheShoeBox.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {


    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testIndexShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser("lucho@example.com")
    public void testHomeShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections"))
                .andExpect(view().name("home"));
    }

}