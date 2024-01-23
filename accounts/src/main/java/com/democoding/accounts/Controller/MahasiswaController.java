package com.democoding.accounts.Controller;

import com.democoding.accounts.Dto.*;
import com.democoding.accounts.Service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MahasiswaController {
    @Autowired
    private MahasiswaService mahasiswaService;
    @GetMapping("/viewmahasiwa")
    public MahasiswaDto viewMahasiswa(@RequestParam("nim") String nim) {
        return mahasiswaService.listMahasiswa(nim);
    }

    @PostMapping("/inputmahasiwa")
    public MessageDto viewMahasiswa(@RequestBody RequestMahasiswaDto request) {
        return mahasiswaService.inputDataMahasiswa(request);
    }

    @PutMapping("/ipkmahasiwa")
    public MessageDto ipkMahasiswa(@RequestBody UpdateIpkRequestDto request) {
        return mahasiswaService.IpkMahasiswa(request);
    }
}
