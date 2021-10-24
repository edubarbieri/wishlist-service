package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

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
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User("test", email)));
        var input = new CreateUserInput("eduardo santos", email);
        try {
            underTest.execute(input);
        }catch (DomainException e){
            Assertions.assertThat(e).isInstanceOf(EmailAlreadyRegistered.class);
        }
    }
    @Test
    void shouldSaveNewUserInRepository() {
        var input = new CreateUserInput("eduardo santos", "teste@hotmail.com");
        underTest.execute(input);
        verify(this.userRepository).save(any());
    }
}