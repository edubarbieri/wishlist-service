package br.com.edubarbieri.whishlist.infra.repository.gateway;

import br.com.edubarbieri.whishlist.application.dto.ProductResponse;
import br.com.edubarbieri.whishlist.infra.clients.ProductServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductGatewayTest {

    @Mock
    private ProductServiceClient productClient;

    private ProductGateway underTest;

    @BeforeEach
    void setUp() {
        openMocks(this);
        this.underTest = new ProductGateway(this.productClient);
    }

    @Test
    void shouldReturnProductById() {
        var productId = "5b7411db-9b78-42e3-a8fd-59fb012783e9";
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId("5b7411db-9b78-42e3-a8fd-59fb012783e9");
        ResponseEntity<ProductResponse> response = new ResponseEntity<>(productResponse, HttpStatus.OK);
        when(productClient.getProduct(productId)).thenReturn(response);

        var product = underTest.findById(productId);
        if (product.isEmpty()) {
            fail("Service not return mocked product");
        }

        assertThat(product.get().getId()).isEqualTo(productId);
    }

    @Test
    void shouldReturnEmptyWhenClientReturn404() {
        var productId = "5b7411db-9b78-42e3-a8fd-59fb012783e9";
        ResponseEntity<ProductResponse> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(productClient.getProduct(productId)).thenReturn(response);

        var product = underTest.findById(productId);

        assertThat(product).isEmpty();
    }
}