package br.com.edubarbieri.whishlist.infra.repository;

import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory implements AbstractRepositoryFactory {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private WishListRepository wishListRepository;

    public RepositoryFactory(ProductRepository productRepository, UserRepository userRepository, WishListRepository wishListRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public ProductRepository createProductRepository() {
        return this.productRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return this.userRepository;
    }

    @Override
    public WishListRepository createWishListRepository() {
        return this.wishListRepository;
    }
}
