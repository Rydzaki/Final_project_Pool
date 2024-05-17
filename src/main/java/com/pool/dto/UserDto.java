package com.pool.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phoneNumber;


    private String message;

}
