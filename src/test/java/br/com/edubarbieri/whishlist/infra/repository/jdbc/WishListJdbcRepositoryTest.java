package br.com.edubarbieri.whishlist.infra.repository.jdbc;

import br.com.edubarbieri.whishlist.domain.entity.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@JdbcTest
class WishListJdbcRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private WishListJdbcRepository underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new WishListJdbcRepository(this.jdbcTemplate);
        jdbcTemplate.update("delete from ml_user_wishlist");
    }

    @Test
    void shouldSaveUserWishList() {
        var userId = "5b7411db-9b78-42e3-a8fd-59fb012783e9";
        var userWishlist = new WishList(userId);
        userWishlist.addProduct("product0");
        userWishlist.addProduct("product1");
        userWishlist.addProduct("product2");

        underTest.save(userWishlist);

        var findResult = underTest.findByUserId(userId);
        if (findResult.isEmpty()) {
            fail("Could not find previous save wishlist");
        }
        assertThat(findResult.get().getProductsId().size()).isEqualTo(3);
    }

    @Test
    void shouldReturnEmptyIfUserNotHaveWishlist() {
        var userId = "7d51e622-ff5b-4704-81df-ebe23a021735";

        var findResult = underTest.findByUserId(userId);

        assertThat(findResult.isEmpty()).isTrue();
    }
}