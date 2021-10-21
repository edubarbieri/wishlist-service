package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public abstract class BaseUseCaseTest {
    @Mock
    protected ProductRepository productRepository;
    @Mock
    protected UserRepository userRepository;
    @Mock
    protected WishListRepository wishListRepository;
    @Mock
    protected AbstractRepositoryFactory repositoryFactory;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(repositoryFactory.createProductRepository()).thenReturn(productRepository);
        when(repositoryFactory.createUserRepository()).thenReturn(userRepository);
        when(repositoryFactory.createWishListRepository()).thenReturn(wishListRepository);
    }
}
