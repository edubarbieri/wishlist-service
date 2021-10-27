package br.com.edubarbieri.whishlist.infra.repository.jdbc;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserJdbcRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        if (!userExists(user)) {
            insert(user);
            return;
        }
        update(user);
    }

    private boolean userExists(User user) {
        if (user.getId() == null) {
            return false;
        }
        var queryResult = jdbcTemplate
                .queryForObject("select count(1) from ml_user where id = ?",
                        Integer.class, user.getId());

        return queryResult > 0;
    }

    private void insert(User user) {
        jdbcTemplate.update("insert into ml_user(name, email, password) values (?,?,?)",
                user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
    }

    private void update(User user) {
        jdbcTemplate.update("update ml_user set name = ?, email = ?, password = ? where id = ?",
                user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return queryUser("select id, name, email, password from ml_user where email = ?", email);
    }

    private Optional<User> queryUser(String sql, Object parameter) {
        var result = jdbcTemplate.query(sql, this::mapUserRow, parameter);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    private User mapUserRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(String id) {
        return queryUser("select id, name, email, password from ml_user where id = CAST(? as uuid)", id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select id, name, email, password from ml_user", this::mapUserRow);
    }

    @Override
    public void deleteById(String userId) {
        jdbcTemplate.update("delete from ml_user where id = ?", userId);
    }

}
