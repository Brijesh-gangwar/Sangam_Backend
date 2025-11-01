package com.example.jwt_project.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public  String greet(HttpServletRequest request){
        return "hello jwt project " + request.getSession().getId();
    }
}
