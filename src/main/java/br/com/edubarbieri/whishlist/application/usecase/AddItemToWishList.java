package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.AddItemToWishListInput;
import br.com.edubarbieri.whishlist.domain.entity.WishList;
import br.com.edubarbieri.whishlist.domain.exception.ProductNotFound;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;

public class AddItemToWishList {

    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;


    public AddItemToWishList(AbstractRepositoryFactory repositoryFactory) {
        this.productRepository = repositoryFactory.createProductRepository();
        this.wishListRepository = repositoryFactory.createWishListRepository();
        this.userRepository = repositoryFactory.createUserRepository();
    }

    public void execute(AddItemToWishListInput input) {
        if(this.userRepository.findById(input.getUserId()).isEmpty()){
            throw new UserNotFound(input.getUserId());
        }
        if (productRepository.findById(input.getProductId()).isEmpty()) {
            throw new ProductNotFound(input.getProductId());
        }
        var userWishList = getUserWishList(input.getUserId());
        userWishList.addProduct(input.getProductId());
        wishListRepository.save(userWishList);
    }

    private WishList getUserWishList(String userId) {
        return wishListRepository
                .findByUserId(userId)
                .orElse(new WishList(userId));
    }
}
