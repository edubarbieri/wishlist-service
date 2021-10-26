package br.com.edubarbieri.whishlist.domain.respository;

import br.com.edubarbieri.whishlist.domain.entity.WishList;

import java.util.Optional;

public interface WishListReadRepository {
    Optional<WishList> findByUserId(String userId);
}
