package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //    @Autowired
//    private ShoeRepository shoeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        UserEntity testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com");

        testUser.setPassword(passwordEncoder.encode("test"));

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
//        shoeRepository.deleteAll();
        //roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void showLoginWithoutAuthenticatedUserShouldReturnLoginPage() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/users/login")
                        .param("email", "userTest@none.com")
                        .param("password", "test")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testLoginUserNotSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("email", "asx@y.z")
                        .param("password", "1111")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
//                .andExpect(flash().attribute("bad_credentials", true))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    void showRegisterForm() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testPostRegisterShouldRegisterUserWithCorrectFields() throws Exception {
        if (roleRepository.count() == 0) {
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            roleRepository.save(userRole);
        }

        mockMvc.perform(post("/users/register").
                        param("username", "username").
                        param("email", "email@abv.com").
                        param("firstname", "firstname").
                        param("lastname", "lastname").
                        param("password", "11111").
                        param("confirmPassword", "11111").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));

        Optional<UserEntity> newUserOpt = userRepository.findByUsername("username");

        Assertions.assertTrue(newUserOpt.isPresent());

        UserEntity newUser = newUserOpt.get();
        Assertions.assertEquals(newUser.getUsername(), "username");
        Assertions.assertEquals(newUser.getEmail(), "email@abv.com");
        Assertions.assertEquals(newUser.getFirstName(), "firstname");
        Assertions.assertEquals(newUser.getLastName(), "lastname");
        Assertions.assertEquals(2, userRepository.count());
    }

    @Test
    void testRegisterUserWithDifferntPasswords() throws Exception {
        mockMvc.perform(post("/users/register").param("username", "username").//
                param("password", "passs").//
                param("confirmPassword", "confirmPassword").//
                param("firstname", "fullanem").//
                param("lastname", "fullanem").//
                param("email", "adss@ad.com").//
                with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/users/register"));
    }

    @Test
    void testRegisterUserWithNotUniqueName() throws Exception {
        mockMvc.perform(post("/users/register").
                param("username", "admin").
                param("email", "email@abv.com").
                param("firstname", "firstname").
                param("lastname", "lastname").
                param("password", "11111").
                param("confirmPassword", "11111").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED));

        mockMvc.perform(post("/users/register")
                .param("username", "admin").//
                param("password", "passs").//
                param("confirmPassword", "passs").//
                param("firstname", "fullanem").//
                param("lastname", "fullanem").//
                param("email", "adss@ad.com").//
                with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/users/register"));
    }

}