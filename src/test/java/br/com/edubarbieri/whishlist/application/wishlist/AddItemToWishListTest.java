package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.BaseUseCaseTest;
import br.com.edubarbieri.whishlist.domain.entity.Product;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.entity.WishList;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.ProductNotFound;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddItemToWishListTest extends BaseUseCaseTest {

    @Test
    void shouldNotAddNonExistentProductInWishList() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        when(productRepository.findById("product123")).thenReturn(Optional.empty());
        when(userRepository.findById("user123")).thenReturn(Optional.of(new User("user123", "teste", "teste@gmail.com", "password")));
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
        when(userRepository.findById("user123")).thenReturn(Optional.of(new User("user123", "teste", "teste@gmail.com", "password")));

        underTest.execute(input);

        assertThat(wishList.getProductsId()).contains("product123");
        verify(wishListRepository).save(wishList);
        verify(eventRepository).publish(any());
    }

    @Test
    void shouldCreateNewWishListIfUserNotHaveWishList() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        when(productRepository.findById("product123")).thenReturn(Optional.of(new Product("product123")));
        when(wishListRepository.findByUserId("user123")).thenReturn(Optional.empty());
        when(userRepository.findById("user123")).thenReturn(Optional.of(new User("user123", "teste", "teste@gmail.com", "password")));

        underTest.execute(input);

        verify(wishListRepository).save(any());
        verify(eventRepository).publish(any());
    }

    @Test
    void shouldNotAddInWishListIfUserNotExists() {
        var underTest = new AddItemToWishList(repositoryFactory);
        var input = new AddItemToWishListInput("user123", "product123");
        when(productRepository.findById("product123")).thenReturn(Optional.of(new Product("product123")));
        try {
            underTest.execute(input);
            fail("Could not add item in wishlist when user not exists");
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(UserNotFound.class);
        }
    }
}