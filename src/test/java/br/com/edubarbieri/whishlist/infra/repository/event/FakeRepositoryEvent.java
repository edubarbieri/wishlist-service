package br.com.edubarbieri.whishlist.infra.repository.event;

import br.com.edubarbieri.whishlist.application.events.Event;
import br.com.edubarbieri.whishlist.application.events.EventRepository;
import org.springframework.boot.test.context.TestConfiguration;

import javax.print.attribute.HashDocAttributeSet;
import java.util.HashMap;
import java.util.Map;


public class FakeRepositoryEvent implements EventRepository {

    private final Map<String, Event> store;

    public FakeRepositoryEvent() {
        this.store = new HashMap<>();
    }

    @Override
    public void publish(Event event) {
        this.store.put(event.getId(), event);
    }
}
