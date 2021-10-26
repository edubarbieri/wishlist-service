package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
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
    private final EventRepository eventRepository;


    public AddItemToWishList(AbstractRepositoryFactory repositoryFactory) {
        this.productRepository = repositoryFactory.createProductRepository();
        this.wishListRepository = repositoryFactory.createWishListRepository();
        this.userRepository = repositoryFactory.createUserRepository();
        this.eventRepository = repositoryFactory.createEventRepository();
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
        eventRepository.publish(new WishListUpdatedEvent(userWishList.getUserId(), userWishList.getProductsId()));
    }

    private WishList getUserWishList(String userId) {
        return wishListRepository
                .findByUserId(userId)
                .orElse(new WishList(userId));
    }
}
