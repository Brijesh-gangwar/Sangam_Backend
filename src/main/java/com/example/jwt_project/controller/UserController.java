package com.example.jwt_project.controller;


import com.example.jwt_project.model.Users;
import com.example.jwt_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public Users register(@RequestBody Users user){


        return  userService.register(user);
    }


    @PostMapping("/login")
    public  String login(@RequestBody Users user) {
//        System.out.println(user);
        return userService.verify(user);
    }


    // Add roles to user by ID
    @PutMapping("/user/{id}/roles/add")
    @PreAuthorize("hasAuthority('PERM_USER_ROLEADD')")
    public ResponseEntity<?> addRole(@PathVariable String id, @RequestParam String role) {
        try {
            Users updatedUser = userService.addRole(id, role);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Remove roles from user by ID
    @DeleteMapping("/user/{id}/roles/remove")
    @PreAuthorize("hasAuthority('PERM_USER_ROLEREM')")
    public ResponseEntity<?> removeRole(@PathVariable String id, @RequestParam String role) {
        try {
            Users updatedUser = userService.removeRole(id, role);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
