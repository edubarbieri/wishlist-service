package br.com.edubarbieri.whishlist.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class UserTest {

    @Test
    void shouldCreateUser(){
        var client = new User("Fake Name", "test@gmail.com");
        assertThat(client).isNotNull();
    }
    @Test
    void shouldValidateEmailToCreateUser(){
        var throwable = catchThrowable(() -> new User("Fake Name", "invade_email"));
        assertThat(throwable).hasMessageContaining("Invalid email");
    }
}