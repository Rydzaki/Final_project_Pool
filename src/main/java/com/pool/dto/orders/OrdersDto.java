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

public class OrdersDto {
    private Integer userId;
    private double summa;
    private Integer itemsCount;
    private ZonedDateTime date;

}
