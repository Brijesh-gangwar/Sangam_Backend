package com.example.jwt_project.service;


import com.example.jwt_project.model.Users;
import com.example.jwt_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTokenService jwTokenService;

    public Users register (Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            // default to USER role
            user.setRoles(java.util.List.of("ROLE_USER"));
        }
        return  userRepo.save(user);
    }

    public  String verify(Users user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    if(authentication.isAuthenticated()){
        return  jwTokenService.generatetoken(user.getUsername());

    }
    return "fails";

    }


    // Add a role to a user
    public Users addRole(String userId, String role) {
        Optional<Users> existingUser = userRepo.findById(userId);

        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Users user = existingUser.get();
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        } else {
            throw new RuntimeException("User already has role: " + role);
        }

        return userRepo.save(user);
    }

    // Remove a role from a user
    public Users removeRole(String userId, String role) {
        Optional<Users> existingUser = userRepo.findById(userId);

        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Users user = existingUser.get();
        if (user.getRoles() == null || !user.getRoles().contains(role)) {
            throw new RuntimeException("User does not have role: " + role);
        }

        user.getRoles().remove(role);
        return userRepo.save(user);
    }

}
