package br.com.edubarbieri.whishlist.application.wishlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveItemFromWishListInput {
    private String userId;
    private String productId;

    public RemoveItemFromWishListInput(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
