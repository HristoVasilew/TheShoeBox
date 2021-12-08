package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.NewCommentBindingModel;
import TheShoeBox.TheShoeBox.model.entity.Comment;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@WithMockUser("lucho@example.com")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {
    private static final String COMMENT_1 = "hey Spring is cool!";
    private static final String COMMENT_2 = "Well... it is a bit trick sometimes... :(";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;


    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setPassword("password");
        testUser.setUsername("lucho");
        testUser.setEmail("lucho@example.com");
        testUser.setFirstName("lucho balev");
        testUser.setLastName("lucho balev");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        shoeRepository.deleteAll();
        userRepository.deleteAll();
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


    private ShoeEntity initComments(ShoeEntity shoeEntity) {

        Comment comment1 = new Comment();
        comment1.setCreated(LocalDateTime.now());
        comment1.setAuthor(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setShoe(shoeEntity);

        Comment comment2 = new Comment();
        comment2.setCreated(LocalDateTime.now());
        comment2.setAuthor(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setShoe(shoeEntity);

        shoeEntity.setComments(List.of(comment1, comment2));

        return shoeRepository.save(shoeEntity);
    }





    @Test
    void testGetComments() throws Exception {
        var shoe = initComments(initShoe());

        mockMvc.perform(get("/api/" + shoe.getId() + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComments() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel().
                setMessage(COMMENT_1);

        var emptyShoe = initShoe();

        mockMvc.perform(
                        post("/api/" + emptyShoe.getId() + "/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testComment))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyShoe.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

}