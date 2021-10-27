package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.exception.PasswordNotMatch;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.repository.UserRepository;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }

    public void execute(CreateUserInput input) {
        if (this.userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new EmailAlreadyRegistered(input.getEmail());
        }
        if (!input.getPassword().equals(input.getConfirmPassword())) {
            throw new PasswordNotMatch();
        }
        var user = new User(input.getName(), input.getEmail(), input.getPassword());
        this.userRepository.save(user);
    }
}
