package br.com.edubarbieri.whishlist.infra.repository.jdbc;

import br.com.edubarbieri.whishlist.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@JdbcTest
class UserJdbcRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserJdbcRepository underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new UserJdbcRepository(this.jdbcTemplate);
        jdbcTemplate.update("delete from ml_user");
    }

    @Test
    void shouldCreateNewUser() {
        var user = new User("eduardo", "duduardo23@gmail.com", "teste12");
        underTest.save(user);
        assertThat(jdbcTemplate.queryForObject("select count(1) from ml_user", Integer.class)).isPositive();
    }

    @Test
    void shouldNotCreateTwoUserWithSameEmail() {
        var user = new User("eduardo", "duduardo23@gmail.com", "teste12");
        underTest.save(user);
        try {
            underTest.save(user);
            fail("Should not save two user with same email");
        }catch (Exception e){
            assertThat(e).isInstanceOf(DuplicateKeyException.class);
        }
    }

    @Test
    void shouldFindUserByIdAndUpdate() {
        var userId = "5b7411db-9b78-42e3-a8fd-59fb012783e9";
        jdbcTemplate.update("insert into ml_user(id, name, email, password) values (?,?,?,?)",
                userId, "eduardo", "duduardo23@gmail.com", "teste12");
        var queryResult = underTest.findById(userId);
        if (queryResult.isEmpty()) {
            fail("Could not find user by exist id");
        }
        var user = queryResult.get();
        user.setName("Carlos Alberto");

        underTest.save(user);

        queryResult = underTest.findById(userId);
        if (queryResult.isEmpty()) {
            fail("Could not find user by exist id");
        }
        assertThat(queryResult.get().getName()).isEqualTo("Carlos Alberto");
    }

    @Test
    void shouldReturnEmptyIfUserWithEmailNotExists() {
        var userByEmail = underTest.findByEmail("notexist@gmail.com");
        assertThat(userByEmail).isEmpty();
    }

    @Test
    void shouldDeleteUser() {
        var userId = "5b7411db-9b78-42e3-a8fd-59fb012783e9";
        jdbcTemplate.update("insert into ml_user(id, name, email, password) values (?,?,?,?)",
                userId, "eduardo", "duduardo23@gmail.com", "teste12");

        underTest.deleteById(userId);

        var queryResult = underTest.findById(userId);
        assertThat(queryResult).isEmpty();
    }

}