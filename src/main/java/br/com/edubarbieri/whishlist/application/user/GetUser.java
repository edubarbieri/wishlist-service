package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

public class GetUser {

    private final UserRepository userRepository;

    public GetUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }
    public GetUserOutput execute(String userId) {
        var user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFound(userId));
        return new GetUserOutput(user.getId(), user.getName(), user.getEmail());
    }
}
