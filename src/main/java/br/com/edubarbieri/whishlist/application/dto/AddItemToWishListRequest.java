package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddItemToWishListRequest {
    @NotEmpty
    private String productId;
}
