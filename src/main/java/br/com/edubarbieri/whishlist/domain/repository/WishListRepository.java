package br.com.edubarbieri.whishlist.domain.repository;

import br.com.edubarbieri.whishlist.domain.entity.WishList;

import java.util.Optional;

public interface WishListRepository {
    void save(WishList wishList);
    Optional<WishList> findByUserId(String userId);
}
