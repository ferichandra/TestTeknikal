package com.democoding.accounts.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class KonsumenRequestDto {
    private String name;
    private String alamat;
    private String kota;
    private String provinsi;
    private Date tglRegistrasi;
    private Integer status;
}
