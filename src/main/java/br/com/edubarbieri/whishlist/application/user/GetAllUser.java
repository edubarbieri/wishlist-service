package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUser {

    private final UserRepository userRepository;

    public GetAllUser(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }
    public List<GetUserOutput> execute() {
        return userRepository.findAll()
                .stream()
                .map(user -> new GetUserOutput(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
