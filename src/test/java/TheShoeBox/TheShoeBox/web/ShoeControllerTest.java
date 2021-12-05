package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class ShoeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ShoeRepository shoeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    private void initComments() {

        UserEntity testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com");

        testUser.setPassword(passwordEncoder.encode("test"));

        testUser = userRepository.save(testUser);


        ShoeEntity shoe = new ShoeEntity();
        shoe
                .setModel("nike")
                .setBrand("vans")
                .setLocation("ulicata ulicataulicataulicataulicataulicata")
                .setSize(new BigDecimal(35))
                .setImageUrl("")
                .setCreator(testUser)
                .setDescription("descripption")
                .setPrice(new BigDecimal(35))
                .setId(1L);

        shoeRepository.save(shoe);
    }


    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void collectionsAll() throws Exception {
        initComments();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void testCreateShoePage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections/create-shoe"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-shoe"));
    }

    @Test
    @WithMockUser(username="admin")
    public void testAddToCart() throws Exception {
       initComments();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections/1/add-to-cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-to-cart"));
    }

    @Test
    @WithMockUser(username="admin")
    public void testDetails() throws Exception {
        initComments();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections/1/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("details"));
    }

}