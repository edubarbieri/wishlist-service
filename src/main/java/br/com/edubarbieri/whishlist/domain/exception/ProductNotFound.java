package br.com.edubarbieri.whishlist.domain.exception;

public class ProductNotFound extends DomainException {
    public ProductNotFound(String productId) {
        super(String.format("Could not find product with id %s", productId));
    }
}
