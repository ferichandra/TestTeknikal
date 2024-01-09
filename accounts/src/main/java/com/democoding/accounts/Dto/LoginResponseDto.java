package com.democoding.accounts.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class LoginResponseDto {
    private String message;
    private String token;
    private Date expiredAt;

}
