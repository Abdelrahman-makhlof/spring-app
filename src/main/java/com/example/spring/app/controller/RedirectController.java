package com.example.spring.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1")

public class RedirectController {

    @GetMapping("/redirect")
    public RedirectView redirectV2(HttpServletRequest request) {
        var url = "/api/v1/hello";
        if (request.getQueryString() != null)
            url += "?" + request.getQueryString();
        return new RedirectView(url); // Redirects to /target within the same app
    }
}
