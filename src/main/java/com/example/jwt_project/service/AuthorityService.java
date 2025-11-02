package com.example.jwt_project.service;

import com.example.jwt_project.model.Role;
import com.example.jwt_project.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private RoleRepo roleRepo;

    // Build authorities from user's role names (e.g., "ADMIN" or "ROLE_ADMIN")
    public Collection<GrantedAuthority> buildAuthorities(List<String> userRoles){
        List<GrantedAuthority> auths = new ArrayList<>();
        if (userRoles == null) return auths;

        for (String r : userRoles){
            String roleName = r.startsWith("ROLE_") ? r : "ROLE_" + r;
            auths.add(new SimpleGrantedAuthority(roleName));

            String lookupName = roleName.replace("ROLE_","");
            Role role = roleRepo.findByName(lookupName);
            if (role != null && role.getPermissions() != null){
                for (String p : role.getPermissions()){
                    String perm = p.startsWith("PERM_") ? p : ("PERM_" + p);
                    auths.add(new SimpleGrantedAuthority(perm));
                }
            }
        }

        return auths;
    }
}
