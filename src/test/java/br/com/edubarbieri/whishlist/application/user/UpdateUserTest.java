package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateUserTest extends BaseUseCaseTest {

    private UpdateUser underTest;

    @BeforeEach
    void setUp() {
        super.setUp();
        underTest = new UpdateUser(repositoryFactory);
    }
    @Test
    void shouldNotUpdateUserThatNotExists() {
        var updateUserInput = new UpdateUserInput("user2", "Jorge Santos Silva", "test@test.com", "teste12", "teste12");
        try {
            underTest.execute(updateUserInput);
            fail("Could not update user that not exists");
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(UserNotFound.class);
        }
    }

    @Test
    void shouldNotUpdateEmailUsedByOtherUser() {
        var updateUserInput = new UpdateUserInput("user2", "Jorge Santos Silva", "test@test.com", "teste12", "teste12");
        when(userRepository.findById("user2"))
                .thenReturn(Optional.of(new User("user2", "Jorge Santos", "jorge.santos@test.com", "password")));
        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(new User("user1", "junior", "test@test.com", "password")));
        try {
            underTest.execute(updateUserInput);
            fail("Could not update to email used by other user");
        } catch (DomainException e) {
            assertThat(e).isInstanceOf(EmailAlreadyRegistered.class);
        }
    }
    @Test
    void shouldUpdateUser() {
        var updateUserInput = new UpdateUserInput("user2", "Jorge Santos Silva", "test@test.com", "teste12", "teste12");
        var user = new User("user2", "Jorge Santos", "jorge.santos@test.com", "password");
        when(userRepository.findById("user2"))
                .thenReturn(Optional.of(user));

        underTest.execute(updateUserInput);

        assertThat(user.getName()).isEqualTo(updateUserInput.getName());
        assertThat(user.getEmail()).isEqualTo(updateUserInput.getEmail());
        verify(userRepository).save(user);
    }

}