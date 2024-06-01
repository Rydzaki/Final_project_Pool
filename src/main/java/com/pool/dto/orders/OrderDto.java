package com.pool.dto.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@Builder

public class OrderDto {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String category;
    private Integer itemsCount;
    private String date;
    private List<OrderProductDto> products;
    private double summa;


}
