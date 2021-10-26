package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.BaseUseCaseTest;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.entity.WishList;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RemoveItemFromWishListTest extends BaseUseCaseTest {

    @Test
    void shouldRemoveItemFromWishList() {
        var wishList = new WishList("user123");
        wishList.addProduct("product123");
        when(wishListRepository.findByUserId("user123")).thenReturn(Optional.of(wishList));
        when(userRepository.findById("user123")).thenReturn(Optional.of(new User("user123", "teste", "teste@gmail.com", "password")));

        var underTest = new RemoveItemFromWishList(repositoryFactory);
        var input = new RemoveItemFromWishListInput("user123", "product123");
        underTest.execute(input);

        assertThat(wishList.getProductsId()).isEmpty();
        verify(wishListRepository).save(wishList);
        verify(eventRepository).publish(any());
    }
}