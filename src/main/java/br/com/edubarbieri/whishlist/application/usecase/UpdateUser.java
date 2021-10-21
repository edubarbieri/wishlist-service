package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.UpdateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

public class UpdateUser {

    private final UserRepository userRepository;

    public UpdateUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }

    public void execute(UpdateUserInput input) {
        var user = getUser(input);
        var userByEmail = userRepository.findByEmail(input.getEmail());
        if (userByEmail.isPresent() && !user.getId().equals(userByEmail.get().getId())) {
            throw new EmailAlreadyRegistered(input.getEmail());
        }
        user.setEmail(input.getEmail());
        user.setName(input.getName());
        this.userRepository.save(user);
    }

    private User getUser(UpdateUserInput input) {
        return userRepository
                .findById(input.getId())
                .orElseThrow(() ->  new UserNotFound(input.getId()));
    }
}
