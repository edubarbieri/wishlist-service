package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.AddItemToWishListInput;
import br.com.edubarbieri.whishlist.domain.entity.Product;
import br.com.edubarbieri.whishlist.domain.entity.WishList;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.ProductNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.ProductRepository;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AddItemToWishListTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private WishListRepository wishListRepository;
    @Mock
    private AbstractRepositoryFactory repositoryFactory;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(repositoryFactory.createProductRepository()).thenReturn(productRepository);
        when(repositoryFactory.createWishListRepository()).thenReturn(wishListRepository);
    }

    @Test
    void shouldNotAddNonExistentProductInWishList() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        when(productRepository.findById("product123")).thenReturn(Optional.empty());
        try {
            underTest.execute(input);
            fail();
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(ProductNotFound.class);
        }
    }
    @Test
    void shouldUpdateExistentWishList() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        var wishList = new WishList("user123");
        when(productRepository.findById("product123")).thenReturn(Optional.of(new Product("product123")));
        when(wishListRepository.findByUserId("user123")).thenReturn(Optional.of(wishList));

        underTest.execute(input);

        assertThat(wishList.getProductsId()).contains("product123");
        verify(wishListRepository).save(wishList);
    }

    @Test
    void shouldCreateNewWishListIfUserNotHaveWishList() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        when(productRepository.findById("product123")).thenReturn(Optional.of(new Product("product123")));
        when(wishListRepository.findByUserId("user123")).thenReturn(Optional.empty());

        underTest.execute(input);

        verify(wishListRepository).save(any());
    }

}