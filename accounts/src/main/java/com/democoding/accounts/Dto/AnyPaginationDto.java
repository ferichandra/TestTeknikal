package com.democoding.accounts.Dto;

import lombok.Data;

import java.util.List;
@Data
public class AnyPaginationDto {
    private int currentPage;
    private int totalPage;
    private Long totalRecord;
    private List<?> data;
}
