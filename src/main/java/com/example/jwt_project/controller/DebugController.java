package com.example.jwt_project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DebugController {

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> m = new HashMap<>();
        if (authentication == null) {
            m.put("authenticated", false);
            return m;
        }
        m.put("authenticated", true);
        m.put("name", authentication.getName());
        m.put("authorities", authentication.getAuthorities());
        return m;
    }
}
