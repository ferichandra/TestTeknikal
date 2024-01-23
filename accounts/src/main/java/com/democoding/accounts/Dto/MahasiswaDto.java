package com.democoding.accounts.Dto;

import com.democoding.accounts.Entity.Matakuliah;
import com.democoding.accounts.Entity.Nilai;
import lombok.Data;

import java.util.List;

@Data
public class MahasiswaDto {
    private String nama;
    private String nim;
    private Integer age;
    private List<NilaiDto>nilai;
}
