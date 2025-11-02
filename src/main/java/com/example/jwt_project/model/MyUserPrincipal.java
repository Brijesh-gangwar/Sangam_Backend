package com.example.jwt_project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;



public class MyUserPrincipal implements UserDetails {

    private Users user;

    public MyUserPrincipal(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        java.util.List<String> roles = user.getRoles();
        if (roles == null || roles.isEmpty()) {
            // default role
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }

        java.util.List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>();
        for (String r : roles) {
            // ensure role names are prefixed with ROLE_
            String roleName = r.startsWith("ROLE_") ? r : ("ROLE_" + r);
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
