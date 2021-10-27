package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.AuthenticationRequest;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.infra.repository.RepositoryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class LoginControllerTest extends BaseWebTest {

    @Autowired
    private RepositoryFactory repositoryFactory;


    @BeforeEach
    void setUp() {
        var userRepository = repositoryFactory.createUserRepository();
        if(userRepository.findByEmail("duduardo23@gmail.com").isEmpty()){
            var user = new User("Eduardo", "duduardo23@gmail.com", "teste12");
            userRepository.save(user);
        }
    }

    @AfterEach
    void tearDown() {
        var userRepository = repositoryFactory.createUserRepository();
        var user = userRepository.findByEmail("duduardo23@gmail.com");
        if(user.isPresent()){
            userRepository.deleteById(user.get().getId());
        }
    }

    @Test
    void shouldLogin() throws Exception {
        var request = new AuthenticationRequest();
        request.setLogin("duduardo23@gmail.com");
        request.setPassword("teste12");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldLoginWithInvalidPassword() throws Exception {
        var request = new AuthenticationRequest();
        request.setLogin("duduardo23@gmail.com");
        request.setPassword("errrour");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void  shouldLoginWithInvalidEmail() throws Exception {
        var request = new AuthenticationRequest();
        request.setLogin("wrong_email@gmail.com");
        request.setPassword("teste12");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}