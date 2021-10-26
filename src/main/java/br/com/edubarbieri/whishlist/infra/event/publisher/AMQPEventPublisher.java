package br.com.edubarbieri.whishlist.infra.event.publisher;

import br.com.edubarbieri.whishlist.application.events.Event;
import br.com.edubarbieri.whishlist.application.events.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AMQPEventPublisher implements EventRepository {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public AMQPEventPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(Event event) {
        try {
            String message = this.objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(event.getType(), "", message);
        } catch (JsonProcessingException e) {
            log.error("error on publishing event", e);
        }
    }
}
