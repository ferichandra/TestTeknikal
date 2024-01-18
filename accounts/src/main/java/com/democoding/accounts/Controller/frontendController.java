package com.democoding.accounts.Controller;

import com.democoding.accounts.Entity.Konsumen;
import com.democoding.accounts.Repository.KonsumenRepository;
import com.democoding.accounts.Service.KonsumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class frontendController {
    @Autowired
    private KonsumenService konsumenService;
    @Autowired
    private KonsumenRepository konsumenRepository;

    @GetMapping("/konsumen")
    public String viewKonsumen(
            Model model) {

        List<Konsumen> result = konsumenRepository.findallKonsumenList();
        model.addAttribute("result", result);

        return "frontend/konsumen"; // Assuming your Thymeleaf template is named "konsumen.html"
    }
}
