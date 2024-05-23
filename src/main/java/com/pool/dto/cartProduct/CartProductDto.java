package com.pool.dto.cartProduct;

import com.pool.pagesSE.BasePage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Builder

public class CartProductDto{

    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private String productName;
}
