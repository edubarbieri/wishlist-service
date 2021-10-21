package br.com.edubarbieri.whishlist.domain.exception;

public class ProductAlreadyInWishList extends DomainException {
    public ProductAlreadyInWishList(String productId) {
        super(String.format("Product with id %s is already on the list ", productId));
    }
}
