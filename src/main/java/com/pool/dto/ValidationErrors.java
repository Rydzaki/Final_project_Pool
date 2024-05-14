package com.pool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ValidationErrors {

    private ValidationError[] errors;
}
