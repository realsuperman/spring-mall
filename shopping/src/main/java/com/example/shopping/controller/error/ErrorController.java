package com.example.shopping.controller.error;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/denied")
    public String deniedPage() { return "error/403"; }

    @GetMapping("/auth")
    public String authErrorPage() { return "error/401"; }
}
