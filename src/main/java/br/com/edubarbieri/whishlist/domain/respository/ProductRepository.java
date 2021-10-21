package br.com.edubarbieri.whishlist.domain.respository;

import br.com.edubarbieri.whishlist.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String id);
}
