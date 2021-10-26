package br.com.edubarbieri.whishlist.domain.entity;

import br.com.edubarbieri.whishlist.domain.exception.ProductAlreadyInWishList;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class WishList {
    private String userId;
    private Set<String> productsId;

    public WishList(String userId) {
        this.userId = userId;
        this.productsId = new HashSet<>();
    }

    public void addProduct(String productId){
        if(this.productsId.contains(productId)){
            throw new ProductAlreadyInWishList(productId);
        }
        this.productsId.add(productId);
    }

}
