package br.com.edubarbieri.whishlist.infra.event.consumer;

import br.com.edubarbieri.whishlist.infra.repository.mongodb.WishListDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class AMQPEventsConsumerTest {

    @Mock
    private WishListDataRepository wishListDataRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldProcessUpdateEvent() {
        var underTest = new AMQPEventsConsumer(new ObjectMapper(), this.wishListDataRepository);
        Message message = new Message("{\"userId\" : \"66894797-ae89-4a76-a3ef-435a3d07d182\", \"productsIds\" : [\"d6d9ab20-ef42-bab5-9b6e-6dc90d981472\"]}".getBytes(StandardCharsets.UTF_8));
        underTest.wishListUpdated(message);
        verify(wishListDataRepository).save(any());
    }

}