package br.com.edubarbieri.whishlist.infra.repository;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListReadRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory implements AbstractRepositoryFactory {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private WishListRepository wishListRepository;

    private EventRepository eventRepository;

    private WishListReadRepository wishListReadRepository;

    public RepositoryFactory(ProductRepository productRepository, UserRepository userRepository, WishListRepository wishListRepository, EventRepository eventRepository, WishListReadRepository wishListReadRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.eventRepository = eventRepository;
        this.wishListReadRepository = wishListReadRepository;
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

    @Override
    public EventRepository createEventRepository() {
        return this.eventRepository;
    }

    @Override
    public WishListReadRepository createWishListReadRepository() {
        return this.wishListReadRepository;
    }
}
