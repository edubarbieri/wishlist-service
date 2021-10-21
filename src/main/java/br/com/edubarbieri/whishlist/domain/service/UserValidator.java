package br.com.edubarbieri.whishlist.domain.service;

import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

public class UserValidator {

    private UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmailAlreadyRegistered(String email) {
        if (this.userRepository.emailAlreadyRegistered(email)) {
            throw new EmailAlreadyRegistered(email);
        }
    }
}
