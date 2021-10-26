package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;

public class RemoveItemFromWishList {

    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    public RemoveItemFromWishList(AbstractRepositoryFactory repositoryFactory) {
        this.productRepository = repositoryFactory.createProductRepository();
        this.wishListRepository = repositoryFactory.createWishListRepository();
        this.userRepository = repositoryFactory.createUserRepository();
        this.eventRepository = repositoryFactory.createEventRepository();
    }

    public void execute(RemoveItemFromWishListInput input) {
        if (this.userRepository.findById(input.getUserId()).isEmpty()) {
            throw new UserNotFound(input.getUserId());
        }
        var userWishList = wishListRepository.findByUserId(input.getUserId());
        if (userWishList.isEmpty()) {
            return;
        }
        userWishList.get().removeProduct(input.getProductId());
        wishListRepository.save(userWishList.get());
        eventRepository.publish(new WishListUpdatedEvent(userWishList.get().getUserId(), userWishList.get().getProductsId()));
    }
}
