package br.com.edubarbieri.whishlist.infra.repository.mongodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Document(collection = "wishlist")
@Getter
@Setter
public class WishListDoc {
    @Id
    private String id;
    private Set<String> productIds;
}
