package com.democoding.accounts.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GenerateTokenRequest {
    private String username;
    private String password;
}
