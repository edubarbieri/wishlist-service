package br.com.edubarbieri.whishlist.infra.security;

import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    public RepositoryUserDetailsService(AbstractRepositoryFactory repositoryFactory) {
        this.userRepository = repositoryFactory.createUserRepository();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not found user with email " + username);
        }
        return new AuthUserDetails(user.get().getId(), user.get().getEmail(), user.get().getPassword());
    }
}
