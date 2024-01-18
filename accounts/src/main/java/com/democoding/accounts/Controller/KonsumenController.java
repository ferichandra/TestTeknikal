package com.democoding.accounts.Controller;

import com.democoding.accounts.Dto.AnyPaginationDto;
import com.democoding.accounts.Dto.EmployeeRequestDto;
import com.democoding.accounts.Dto.KonsumenRequestDto;
import com.democoding.accounts.Dto.MessageDto;
import com.democoding.accounts.Service.KonsumenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class KonsumenController {
    @Autowired
    private KonsumenService konsumenService;

    @GetMapping("/viewKonsumen")
    public AnyPaginationDto allKonsumen(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize,
                                        @RequestParam("name") String name) {
        return konsumenService.viewKonsumen(pageNo, pageSize, name);
    }

    @PostMapping("/createKonsumen")
    public MessageDto addKonsumen(@RequestBody KonsumenRequestDto request) {
        return konsumenService.createKonsumen(request);
    }
    @PutMapping("/updateKonsumen/{id}")
    public MessageDto updateKonsumen(@RequestBody KonsumenRequestDto request, @PathVariable ("id")Long id) {
        return konsumenService.updateKonsumen(request,id);
    }
    @DeleteMapping("/deleteKonsumen/{id}")

    public MessageDto deleteKonsumen(@PathVariable("id") Long id) {
        return konsumenService.deleteKonsumen(id);
    }
}
