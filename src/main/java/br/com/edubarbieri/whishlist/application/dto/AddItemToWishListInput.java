package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemToWishListInput {
    private String userId;
    private String productId;

    public AddItemToWishListInput(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
