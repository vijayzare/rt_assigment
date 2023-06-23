package com.rt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/api/secure")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String secureEndpoint() {
        return "This is a secure endpoint!";
    }

    @GetMapping("/api/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }
}
