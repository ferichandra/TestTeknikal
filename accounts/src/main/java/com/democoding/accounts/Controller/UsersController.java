package com.democoding.accounts.Controller;

import com.democoding.accounts.Dto.LoginRequestDto;
import com.democoding.accounts.Dto.LoginResponseDto;
import com.democoding.accounts.Dto.UsersResponseDetailDto;
import com.democoding.accounts.Entity.Users;
import com.democoding.accounts.Service.UsersService;
import com.democoding.accounts.Service.library.AuthSecurity;
import com.democoding.accounts.Service.library.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return usersService.findUser(loginRequestDto);
    }
    @GetMapping("/login/detail")

    @ResponseBody
    public UsersResponseDetailDto detailLogin(@RequestHeader("Authorization")String auth ){
        String token = tokenProvider.getJWTfromRequest(auth);
        tokenProvider.validateToken(token);
        return usersService.getActiveUserLogin(token);
    }
}
