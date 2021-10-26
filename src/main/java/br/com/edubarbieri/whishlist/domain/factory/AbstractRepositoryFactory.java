package br.com.edubarbieri.whishlist.domain.factory;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListReadRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;

public interface AbstractRepositoryFactory {
    ProductRepository createProductRepository();
    UserRepository createUserRepository();
    WishListRepository createWishListRepository();
    EventRepository createEventRepository();
    WishListReadRepository createWishListReadRepository();
}
