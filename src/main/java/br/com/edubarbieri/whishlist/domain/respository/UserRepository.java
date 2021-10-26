package br.com.edubarbieri.whishlist.domain.respository;


import br.com.edubarbieri.whishlist.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    List<User> findAll();
    void deleteById(String userId);
}
