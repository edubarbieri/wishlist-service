package br.com.edubarbieri.whishlist.infra.clients;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceClientTest {

    @Autowired
    private ProductServiceClient underTest;

    @Test()
    @Disabled()
    void shouldReturnProductById(){
        var responseEntity = underTest.getProduct("79b1c283-00ef-6b22-1c8d-b0721999e2f0");

        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Aparelho de Musculação Academia Particular");
    }
}