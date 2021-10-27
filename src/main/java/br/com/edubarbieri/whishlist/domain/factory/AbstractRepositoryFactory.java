package br.com.edubarbieri.whishlist.domain.factory;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.domain.repository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.repository.UserRepository;
import br.com.edubarbieri.whishlist.domain.repository.WishListReadRepository;
import br.com.edubarbieri.whishlist.domain.repository.WishListRepository;

public interface AbstractRepositoryFactory {
    ProductRepository createProductRepository();
    UserRepository createUserRepository();
    WishListRepository createWishListRepository();
    EventRepository createEventRepository();
    WishListReadRepository createWishListReadRepository();
}
