package com.example.spring.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1")

public class RedirectController {

    @GetMapping("/redirect")
    public RedirectView redirectV2() {
        return new RedirectView("/api/v1/hello"); // Redirects to /target within the same app
    }
}
