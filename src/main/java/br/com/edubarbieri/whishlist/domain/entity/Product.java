package br.com.edubarbieri.whishlist.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;

    public Product(String id) {
        this.id = id;
    }
}
