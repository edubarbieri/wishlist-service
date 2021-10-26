package br.com.edubarbieri.whishlist.infra.event.consumer;

import br.com.edubarbieri.whishlist.application.wishlist.WishListUpdatedEvent;
import br.com.edubarbieri.whishlist.infra.repository.mongodb.WishListDataRepository;
import br.com.edubarbieri.whishlist.infra.repository.mongodb.WishListDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class AMQPEventsConsumer {

    private ObjectMapper objectMapper;
    private WishListDataRepository wishListDataRepository;

    public AMQPEventsConsumer(ObjectMapper objectMapper, WishListDataRepository wishListDataRepository) {
        this.objectMapper = objectMapper;
        this.wishListDataRepository = wishListDataRepository;
    }

    @RabbitListener(queues = WishListUpdatedEvent.EVENT_TYPE)
    public void wishListUpdated(Message message) {
        try {
            var event = objectMapper.readValue(message.getBody(), WishListUpdatedEvent.class);
            log.info("processing wishlist {} update event", (event.getUserId()));

            Optional<WishListDoc> wishList = wishListDataRepository.findById(event.getUserId());
            if (wishList.isPresent()) {
                wishList.get().setProductIds(event.getProductsIds());
                wishListDataRepository.save(wishList.get());
            } else {
                WishListDoc doc = new WishListDoc();
                doc.setId(event.getUserId());
                doc.setProductIds(event.getProductsIds());
                wishListDataRepository.save(doc);
            }
        } catch (IOException e) {
            log.error("error consuming update wishlist message", e);
        }
    }
}
