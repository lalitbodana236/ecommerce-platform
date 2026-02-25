package com.platform.user.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TokenResponseDto {
    private String tokenValue;
    private Date expiryAt;
    private String email;
}
