package br.com.edubarbieri.whishlist.application.wishlist;

import br.com.edubarbieri.whishlist.application.events.Event;
import lombok.Getter;

import java.util.Set;

@Getter
public class WishListUpdatedEvent implements Event {
    public static final String EVENT_TYPE = "WishlistUpdated";
    private String userId;
    private Set<String> productsIds;

    public WishListUpdatedEvent(String userId, Set<String> productsIds) {
        this.userId = userId;
        this.productsIds = productsIds;
    }
    public WishListUpdatedEvent() {
    }
    @Override
    public String getType() {
        return EVENT_TYPE;
    }
}
