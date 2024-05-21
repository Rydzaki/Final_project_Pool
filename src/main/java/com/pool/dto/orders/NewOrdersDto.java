package com.pool.dto.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
@Builder

public class NewOrdersDto {

    private Integer id;
    private Integer userId;
    private double summa;
    private Integer itemsCount;
    private String date;

    //    private ZonedDateTime date;
    //private LocalDate date;
}
