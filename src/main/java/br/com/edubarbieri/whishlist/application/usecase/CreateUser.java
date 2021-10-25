package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }

    public void execute(CreateUserInput input){
        if(userRepository.findByEmail(input.getEmail()).isPresent()){
            throw new EmailAlreadyRegistered(input.getEmail());
        }
        //TODO Check if password equal confirmPassword
        var user = new User(input.getName(), input.getEmail(), input.getPassword());
        this.userRepository.save(user);
    }
}
