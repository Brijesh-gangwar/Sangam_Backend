package com.example.jwt_project.service;

import com.example.jwt_project.model.Users;
import com.example.jwt_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AuthorityService authorityService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = userRepo.findByUsername(username);

    if (user == null) {
      System.out.println("user not found");
      throw new UsernameNotFoundException("user not found");
    }

    return new User(user.getUsername(), user.getPassword(), authorityService.buildAuthorities(user.getRoles()));
  }
}
