package br.com.edubarbieri.whishlist.infra.repository.mongodb;

import br.com.edubarbieri.whishlist.domain.entity.WishList;
import br.com.edubarbieri.whishlist.domain.repository.WishListReadRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WishListMongoReadRepository implements WishListReadRepository {

    private WishListDataRepository mongoRepository;

    public WishListMongoReadRepository(WishListDataRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Optional<WishList> findByUserId(String userId) {
        var doc = mongoRepository.findById(userId);
        if (doc.isEmpty()) {
            return Optional.empty();
        }
        var wishList = new WishList(doc.get().getId());
        wishList.setProductsId(doc.get().getProductIds());
        return Optional.of(wishList);
    }
}
