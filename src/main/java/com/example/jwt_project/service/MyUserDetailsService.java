package com.example.jwt_project.service;

import com.example.jwt_project.model.MyUserPrincipal;
import com.example.jwt_project.model.Users;

import com.example.jwt_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Users user = userRepo.findByUsername(username);

      if(user == null){
          System.out.println("uesr not found");
       throw  new UsernameNotFoundException("user not found");

      }

        return new MyUserPrincipal(user);
    }
}
