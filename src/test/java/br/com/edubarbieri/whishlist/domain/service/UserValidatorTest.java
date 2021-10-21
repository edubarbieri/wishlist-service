package br.com.edubarbieri.whishlist.domain.service;

import br.com.edubarbieri.whishlist.domain.entity.User;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.exception.EmailAlreadyRegistered;
import br.com.edubarbieri.whishlist.domain.respository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldThrowsExceptionWhenEmailIsInUse(){
        try {
            var underTest = new UserValidator(this.userRepository);
            var testEmail = "test@test.com";
            when(userRepository.emailAlreadyRegistered(testEmail)).thenReturn(true);
            underTest.validateEmailAlreadyRegistered(testEmail);
            fail();
        }catch (DomainException e){
            Assertions.assertThat(e).isInstanceOf(EmailAlreadyRegistered.class);
        }
    }

}