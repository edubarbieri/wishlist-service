package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemToWishListRequest {
    private String productId;
}
