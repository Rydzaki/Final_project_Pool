package com.pool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class NewProductDto {
    private Integer id;
    private String title;
    private double price;
    private String category;
}
