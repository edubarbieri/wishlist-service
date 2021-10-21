package br.com.edubarbieri.whishlist.domain.respository;


import br.com.edubarbieri.whishlist.domain.entity.User;

public interface UserRepository {
    void save(User user);
    boolean emailAlreadyRegistered(String email);
}
