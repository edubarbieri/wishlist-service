package br.com.edubarbieri.whishlist.infra.repository.gateway;


import br.com.edubarbieri.whishlist.domain.entity.Product;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.infra.clients.ProductServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductGateway implements ProductRepository {

    private ProductServiceClient productClient;

    public ProductGateway(ProductServiceClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public Optional<Product> findById(String id) {
        var response = productClient.getProduct(id);
        if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
            return Optional.empty();
        }
        return Optional.of(ProductAdapter.asEntity(response.getBody()));
    }
}
