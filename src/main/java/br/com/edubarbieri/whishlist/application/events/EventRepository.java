package br.com.edubarbieri.whishlist.application.events;

public interface EventRepository {
    void publish(Event event);
}
