package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.AddItemToWishListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class WishListCommandControllerTest extends BaseWebTest{


    @Test
    void shouldNotCreateWishListForUserThatNotExists() throws Exception {
        var request = new AddItemToWishListRequest();
        request.setProductId("1234");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/4e0d0799-be22-49d5-8b09-75171fd571ee/wishlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
}