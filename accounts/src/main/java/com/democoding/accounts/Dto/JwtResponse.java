package com.democoding.accounts.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class JwtResponse {
    private String token;
    private Date expiredAt;
}

