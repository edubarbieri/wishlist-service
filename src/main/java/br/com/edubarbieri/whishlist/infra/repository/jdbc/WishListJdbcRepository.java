package br.com.edubarbieri.whishlist.infra.repository.jdbc;

import br.com.edubarbieri.whishlist.domain.entity.WishList;
import br.com.edubarbieri.whishlist.domain.respository.WishListRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class WishListJdbcRepository implements WishListRepository {

    private JdbcTemplate jdbcTemplate;

    public WishListJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(WishList wishList) {
        jdbcTemplate.update("delete from ml_user_wishlist where user_id = CAST(? as uuid)", wishList.getUserId());
        for (var productId : wishList.getProductsId()) {
            jdbcTemplate.update("insert into ml_user_wishlist(user_id, product_id) values (CAST(? as uuid), ?)",
                    wishList.getUserId(), productId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WishList> findByUserId(String userId) {
        var queryResult = jdbcTemplate.queryForRowSet("select user_id, product_id from ml_user_wishlist where user_id = CAST(? as uuid)", userId);
        if (!queryResult.next()) {
            return Optional.empty();
        }
        var wishList = new WishList(userId);
        do {
            wishList.addProduct(queryResult.getString("product_id"));
        } while (queryResult.next());
        return Optional.of(wishList);
    }
}
