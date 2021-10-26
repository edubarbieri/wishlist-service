package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.dto.QueryWishListResultItem;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListReadRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetUserWishList {

    private WishListReadRepository wishListRepository;
    private ProductRepository productRepository;

    public GetUserWishList(AbstractRepositoryFactory repositoryFactory) {
        this.wishListRepository = repositoryFactory.createWishListReadRepository();
        this.productRepository = repositoryFactory.createProductRepository();
    }

    public List<QueryWishListResultItem> execute(String userId) {
        var userWishList = this.wishListRepository.findByUserId(userId);
        if (userWishList.isEmpty()) {
            return Collections.emptyList();
        }
        return userWishList.get()
                .getProductsId().stream()
                .map(productId -> productRepository.findById(productId))
                .filter(Optional::isPresent)
                .map(product -> new QueryWishListResultItem(product.get()))
                .collect(Collectors.toList());
    }

}
