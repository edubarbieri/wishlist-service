package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class UserCommandControllerTest extends BaseWebTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() throws Exception {
        var createUserInput = new CreateUserInput("eduardo", "duduardo23@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserInput)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void shouldNotCreateUserWithInvalidEmail() throws Exception {
        var createUserInput = new CreateUserInput("eduardo", "duduardo23");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserInput)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    void shouldRemoveUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/f1bad78c-1b89-4af8-a2f4-8a341f5aae29"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    void shouldRemoveUserThatNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/335dde38-c631-4f88-91ce-6d8f04cf559d"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
}