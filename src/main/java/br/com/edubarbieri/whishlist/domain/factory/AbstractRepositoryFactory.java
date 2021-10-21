package br.com.edubarbieri.whishlist.domain.factory;

import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;

public interface AbstractRepositoryFactory {
    ProductRepository createProductRepository();
    UserRepository createUserRepository();
    WishListRepository createWishListRepository();
}
