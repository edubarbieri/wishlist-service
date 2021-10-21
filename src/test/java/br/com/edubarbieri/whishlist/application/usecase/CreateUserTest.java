package br.com.edubarbieri.whishlist.application.usecase;

import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import br.com.edubarbieri.whishlist.domain.service.UserValidator;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateUserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    private CreateUser underTest;

    @BeforeEach
    void setUp() {
        openMocks(this);
        this.underTest = new CreateUser(this.userRepository, this.userValidator);
    }

    @Test
    void shouldNotCreateDuplicatedEmailUser() {
        var email = "teste@hotmail.com";
        doThrow(new EmailAlreadyRegistered(email)).when(this.userValidator).validateEmailAlreadyRegistered(email);
        var input = new CreateUserInput("eduardo santos", "teste@hotmail.com");
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