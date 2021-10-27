package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.repository.UserRepository;
import br.com.edubarbieri.whishlist.domain.repository.WishListRepository;

public class RemoveItemFromWishList {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    public RemoveItemFromWishList(AbstractRepositoryFactory repositoryFactory) {
        this.wishListRepository = repositoryFactory.createWishListRepository();
        this.userRepository = repositoryFactory.createUserRepository();
        this.eventRepository = repositoryFactory.createEventRepository();
    }

    public void execute(RemoveItemFromWishListInput input) {
        if (this.userRepository.findById(input.getUserId()).isEmpty()) {
            throw new UserNotFound(input.getUserId());
        }
        var userWishList = this.wishListRepository.findByUserId(input.getUserId());
        if (userWishList.isEmpty()) {
            return;
        }
        userWishList.get().removeProduct(input.getProductId());
        this.wishListRepository.save(userWishList.get());
        this.eventRepository.publish(new WishListUpdatedEvent(userWishList.get().getUserId(), userWishList.get().getProductsId()));
    }
}
