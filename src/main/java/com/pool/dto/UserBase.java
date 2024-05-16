package com.pool.dto;

import lombok.Getter;



public class UserBase {

    public RequestDto createAuthRequestDto(String username, String password) {
        return RequestDto.builder()
                .username(username)
                .password(password)
                .build();
    }
    @Getter
    RequestDto auth_validAdmin = createAuthRequestDto("ushakov_test@mail.com", "Pass12345!");
    @Getter
    RequestDto auth_invalid_email = createAuthRequestDto("@mail.com", "Pass12345!");
    @Getter
    RequestDto auth_invalid_pass = createAuthRequestDto("ushakov_test@mail.com", "Pass123455");




}

