package com.pool.dto.orderProductDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class OrderCartProductDto {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
}
