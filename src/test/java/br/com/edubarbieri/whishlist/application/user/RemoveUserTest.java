package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RemoveUserTest extends BaseUseCaseTest {

    private DeleteUser underTest;

    @BeforeEach
    void setUp() {
        super.setUp();
        underTest = new DeleteUser(repositoryFactory);
    }

    @Test
    void shouldRemoveUser() {
        var user = new User("user2", "Jorge Santos", "jorge.santos@test.com", "password");
        when(userRepository.findById("user2"))
                .thenReturn(Optional.of(user));
        underTest.execute("user2");
        verify(userRepository).deleteById("user2");
    }

    @Test
    void shouldNotDeleteUserThatNotExists() {
        try {
            underTest.execute("user2");
            fail("Could not delete user that not exists");
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(UserNotFound.class);
        }
    }

}