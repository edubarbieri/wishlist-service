package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.service.UserValidator;

public class CreateUser {

    private UserRepository userRepository;
    private UserValidator userValidator;

    public CreateUser(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void execute(CreateUserInput input){
        userValidator.validateEmailAlreadyRegistered(input.getEmail());
        var user = new User(input.getName(), input.getEmail());
        this.userRepository.save(user);
    }
}
