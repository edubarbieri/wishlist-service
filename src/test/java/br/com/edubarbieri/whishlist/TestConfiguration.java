package br.com.edubarbieri.whishlist;

import br.com.edubarbieri.whishlist.application.events.EventRepository;
import br.com.edubarbieri.whishlist.infra.repository.event.FakeRepositoryEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {

    @Bean
    public EventRepository eventRepository(){
        return new FakeRepositoryEvent();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
