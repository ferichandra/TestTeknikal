package com.democoding.accounts.Dto;

import com.democoding.accounts.Entity.Job;
import com.democoding.accounts.Entity.UserDetail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UsersResponseDetailDto {
    private String username ;
    private UserDetail detail;
    private List<Job> job;
}
