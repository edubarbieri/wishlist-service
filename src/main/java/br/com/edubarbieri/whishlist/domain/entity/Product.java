package br.com.edubarbieri.whishlist.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private String id;
    private String title;
    private String image;
    private BigDecimal price;
    private BigDecimal reviewScore;


    public Product(String id) {
        this.id = id;
    }
}
