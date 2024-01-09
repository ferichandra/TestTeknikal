package com.democoding.accounts.Service;

import com.democoding.accounts.Dto.JwtResponse;
import com.democoding.accounts.Dto.LoginRequestDto;
import com.democoding.accounts.Dto.LoginResponseDto;
import com.democoding.accounts.Dto.UsersResponseDetailDto;
import com.democoding.accounts.Entity.Users;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Repository.JobRepository;
import com.democoding.accounts.Repository.UserDetailRepository;
import com.democoding.accounts.Repository.UsersRepository;
import com.democoding.accounts.Service.library.AuthSecurity;
import com.democoding.accounts.Service.library.JwtTokenProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthSecurity authSecurity;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private JobRepository jobRepository;

    public LoginResponseDto findUser(LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        String userName = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        Users user = usersRepository.findByUsernameAndPassword(userName, password);

        if (user == null) {
            throw new ResourceNotAcceptableException("Unauthorized");
        }
        JwtResponse tokens = tokenProvider.generateToken(user);
        loginResponseDto.setToken(tokens.getToken());
        loginResponseDto.setExpiredAt(tokens.getExpiredAt());
        loginResponseDto.setMessage("Successfully Login");

        return loginResponseDto;
    }
    public UsersResponseDetailDto getActiveUserLogin(String token) {
        String username;
        if (token == null || token.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            username = authentication.getName();
        } else {
            username = tokenProvider.getUsername(token);
        }
        Users user  = usersRepository.findByUsername(username);
        UsersResponseDetailDto usersResponseDetailDto = new UsersResponseDetailDto();
        usersResponseDetailDto.setUsername(user.getUsername());
        usersResponseDetailDto.setDetail(userDetailRepository.findUserDetailByUserId(user.getId().intValue()));
        usersResponseDetailDto.setJob(jobRepository.findJobByUserId(user.getId().intValue()));
        return usersResponseDetailDto;
    }

}
