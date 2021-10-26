package br.com.edubarbieri.whishlist.infra.repository.gateway;

import br.com.edubarbieri.whishlist.application.dto.ProductResponse;
import br.com.edubarbieri.whishlist.domain.entity.Product;

public class ProductAdapter {

    private ProductAdapter() {
    }

    public static Product asEntity(ProductResponse response){
        var product = new Product(response.getId());
        product.setPrice(response.getPrice());
        product.setImage(response.getImage());
        product.setTitle(response.getTitle());
        product.setReviewScore(response.getReviewScore());
        return product;
    }
}
