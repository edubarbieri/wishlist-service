package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private String id;
    private String title;
    private String brand;
    private String image;
    private BigDecimal price;
    private BigDecimal reviewScore;
}
