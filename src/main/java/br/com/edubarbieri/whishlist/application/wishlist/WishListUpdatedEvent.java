package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.events.Event;
import lombok.Getter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
public class WishListUpdatedEvent extends Event {
    public static final String EVENT_TYPE = "WishlistUpdated";
    private String userId;
    private Set<String> productsIds;

    public WishListUpdatedEvent(String userId, Set<String> productsIds) {
        this.id = UUID.randomUUID().toString();
        this.created = new Date();
        this.userId = userId;
        this.productsIds = productsIds;
    }

    public WishListUpdatedEvent(){
    }

    @Override
    public String getType() {
        return EVENT_TYPE;
    }
}
