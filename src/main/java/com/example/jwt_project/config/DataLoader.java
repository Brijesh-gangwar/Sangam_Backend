package com.example.jwt_project.config;

import com.example.jwt_project.model.Role;
import com.example.jwt_project.repo.RoleRepo;
import com.example.jwt_project.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {
        // create or update default roles so existing DB entries get missing permissions
        List<String> adminPerms = Arrays.asList(
                Permission.STUDENTS_READ.name(),
                Permission.STUDENTS_WRITE.name(),
                Permission.TODOS_CREATE.name(),
                Permission.USERS_MANAGE.name(),
                Permission.TODOS_READ.name()

        );

        Role admin = roleRepo.findByName("ADMIN");
        if (admin == null) {
            admin = new Role("ADMIN", new ArrayList<>(adminPerms));
            roleRepo.save(admin);
        } else {
            Set<String> merged = new LinkedHashSet<>();
            if (admin.getPermissions() != null) merged.addAll(admin.getPermissions());
            merged.addAll(adminPerms);
            admin.setPermissions(new ArrayList<>(merged));
            roleRepo.save(admin);
        }

        List<String> userPerms = Arrays.asList(
                Permission.STUDENTS_READ.name(),
                Permission.TODOS_READ.name()
        );

        Role user = roleRepo.findByName("USER");
        if (user == null) {
            user = new Role("USER", new ArrayList<>(userPerms));
            roleRepo.save(user);
        } else {
            Set<String> merged = new LinkedHashSet<>();
            if (user.getPermissions() != null) merged.addAll(user.getPermissions());
            merged.addAll(userPerms);
            user.setPermissions(new ArrayList<>(merged));
            roleRepo.save(user);
        }
    }

}
