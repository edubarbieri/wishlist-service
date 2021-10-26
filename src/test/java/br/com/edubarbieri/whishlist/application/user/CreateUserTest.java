package br.com.edubarbieri.whishlist.application.user;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class CreateUserTest extends BaseUseCaseTest {

    private CreateUser underTest;

    @BeforeEach
    void setUp() {
        super.setUp();
        this.underTest = new CreateUser(this.repositoryFactory);
    }
    @Test
    void shouldNotCreateDuplicatedEmailUser() {
        var email = "teste@hotmail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User("test", email, "pwd")));
        var input = new CreateUserInput("eduardo santos", email, "pwd", "pwd");
        try {
            underTest.execute(input);
        }catch (DomainException e){
            Assertions.assertThat(e).isInstanceOf(EmailAlreadyRegistered.class);
        }
    }
    @Test
    void shouldSaveNewUserInRepository() {
        var input = new CreateUserInput("eduardo santos", "teste@hotmail.com", "pwd", "pwd");
        underTest.execute(input);
        verify(this.userRepository).save(any());
    }
}