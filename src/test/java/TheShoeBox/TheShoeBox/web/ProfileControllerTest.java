package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository userRoleRepository;

    private UserEntity testUser;

    @PostConstruct
    void setUp() {
        UserEntity testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com");

        testUser.setPassword(passwordEncoder.encode("test"));

        testUser = userRepository.save(testUser);
    }

    @Test
    void showProfileShouldVisualizeUserProfile() throws Exception {
        mockMvc.perform(post("/users/login")
                        .param("email", "userTest@none.com")
                        .param("password", "test")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())

                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    @WithMockUser("testUser")
    void editProfileShouldVisualizeUserProfile() throws Exception {
        mockMvc
                .perform(get("/profile/edit-profile"))
                .andExpect(model().attributeExists("userUpdateBindingModel"));
    }

    @Test
    void getUserProfile() {
    }
}