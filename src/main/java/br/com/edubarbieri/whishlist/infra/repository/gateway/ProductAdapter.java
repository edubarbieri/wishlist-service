package br.com.edubarbieri.whishlist.infra.repository.gateway;

import br.com.edubarbieri.whishlist.application.dto.ProductResponse;
import br.com.edubarbieri.whishlist.domain.entity.Product;

public class ProductAdapter {

    private ProductAdapter() {
    }

    public static Product asEntity(ProductResponse response){
        return new Product(response.getId());
    }
}
