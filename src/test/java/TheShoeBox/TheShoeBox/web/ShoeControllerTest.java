package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @WithMockUser("creator")
    void testPostShoe() throws Exception {

        mockMvc.perform(post("/collections/create-shoe").
                        param("brand", "brand").
                        param("model", "model").
                        param("size", String.valueOf(new BigDecimal(24))).
                        param("location", "Location").
                        param("description", "https://www.obuvki.bg/media/catalog/product/cache/small_image/300x300/0/0/0000208766869_01_plj.jpg").
                        param("price", String.valueOf(new BigDecimal(244))).
                        param("imageUrl", "https://www.obuvki.bg/media/catalog/product/cache/small_image/300x300/0/0/0000208766869_01_plj.jpg").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                ;

        Optional<ShoeEntity> newUserOpt = shoeRepository.findById(1L);

        Assertions.assertTrue(newUserOpt.isPresent());

    }

    @Test
    @WithMockUser(username="admin")
    public void getAddSeasonPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/collections/create-shoe"))
                .andExpect(view().name("create-shoe"));
    }


    private UserEntity testUser;


    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setPassword("password");
        testUser.setUsername("hristo");
        testUser.setEmail("hristo@example.com");
        testUser.setFirstName("hristo hristov");
        testUser.setLastName("hristo hristov");

        testUser = userRepository.save(testUser);
    }

    private ShoeEntity initShoe() {
        ShoeEntity testShoe = new ShoeEntity();
        testShoe
                .setPrice(new BigDecimal(23))
                .setDescription("asdasdads")
                .setCreator(testUser)
                .setImageUrl("http//locale::host/23")
                .setSize(new BigDecimal(23))
                .setLocation("sadasdasfafsf")
                .setModel("sadasasfad")
                .setBrand("asdasdsfadssad");

        return shoeRepository.save(testShoe);
    }


    @Test
    @WithMockUser(username="admin")
    public void testAddToCart() throws Exception {

        var shoe = initShoe();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/collections/"+ shoe.getId() + "/add-to-cart"))
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