package br.com.edubarbieri.whishlist.application.dto;

import br.com.edubarbieri.whishlist.domain.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class QueryWishListResultItem {
    private String productId;
    private String title;
    private String image;
    private BigDecimal price;
    private BigDecimal reviewScore;

    public QueryWishListResultItem(Product product){
        this.productId = product.getId();
        this.title = product.getTitle();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.reviewScore = product.getReviewScore();
    }
}
