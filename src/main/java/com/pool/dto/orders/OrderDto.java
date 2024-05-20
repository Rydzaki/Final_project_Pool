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
    private Integer userId;
    private double summa;
    private Integer itemsCount;
    private String date;
    private List<OrderProductDto> products;


}
