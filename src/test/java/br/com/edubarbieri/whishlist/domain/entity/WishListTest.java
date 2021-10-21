package br.com.edubarbieri.whishlist.domain.entity;

import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.ProductAlreadyInWishList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class WishListTest {

    @Test
    void shouldNotAddSameProductTwiceToWishList() {
        var underTest = new WishList("user123");
        underTest.addProduct("prod1234");
        try {
            underTest.addProduct("prod1234");
            fail();
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(ProductAlreadyInWishList.class);
        }
    }
}