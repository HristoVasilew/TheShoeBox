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
class OrderControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Test
    @WithMockUser("lucho@example.com")
    public void testIndexShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/all"))
                .andExpect(view().name("orders"));
    }
}