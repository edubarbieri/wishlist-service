package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(CreateUserInput input){
        if(userRepository.findByEmail(input.getEmail()).isPresent()){
            throw new EmailAlreadyRegistered(input.getEmail());
        }
        var user = new User(input.getName(), input.getEmail());
        this.userRepository.save(user);
    }
}
