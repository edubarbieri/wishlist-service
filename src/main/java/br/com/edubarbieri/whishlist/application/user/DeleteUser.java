package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.repository.UserRepository;

public class DeleteUser {

    private final UserRepository userRepository;

    public DeleteUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }

    public void execute(String userId) {
        if (this.userRepository.findById(userId).isEmpty()) {
            throw new UserNotFound(userId);
        }
        this.userRepository.deleteById(userId);
    }
}
