package com.pool.dto.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.ZonedDateTime;

@Setter
@Getter
@ToString
@Builder
public class OrderProductDto {

    private int id;
    private int orderId;
    private int productId;
    private int quantity;

}
