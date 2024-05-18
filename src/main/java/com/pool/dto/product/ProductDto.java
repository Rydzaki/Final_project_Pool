package com.pool.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ProductDto {
    private Integer id;
    private String title;
    private double price;
    private String category;
}
