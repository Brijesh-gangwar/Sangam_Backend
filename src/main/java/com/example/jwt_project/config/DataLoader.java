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

        // ADMIN ROLE
        List<String> adminPerms = Arrays.asList(
                Permission.USERS_MANAGE.name(),
                Permission.DEPT_READALL.name(),
                Permission.DEPT_READBYID.name(),
                Permission.DEPT_DELETE.name(),
                Permission.DEPT_CREATE.name(),
                Permission.USER_ROLEADD.name(),
                Permission.USER_ROLEREM.name(),
                Permission.USER_VIEWALL.name(),
                Permission.PROJECT_READ.name(),
                Permission.PROJECT_DELETE.name()

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


        // USER ROLE
        List<String> userPerms = Arrays.asList(
                Permission.DEPT_READALL.name(),
                Permission.PROJECT_READ.name()
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


        // DEPT_HEAD ROLE
        List<String> deptHeadPerms = Arrays.asList(
                Permission.DEPT_READALL.name(),
                Permission.DEPT_READBYID.name(),
                Permission.PROJECT_CREATE.name(),
                Permission.PROJECT_UPDATE.name(),
                Permission.PROJECT_READ.name()
        );

        Role dept_head = roleRepo.findByName("DEPT_HEAD");
        if (dept_head == null) {
            dept_head = new Role("DEPT_HEAD", new ArrayList<>(deptHeadPerms));
            roleRepo.save(dept_head);
        } else {
            Set<String> merged = new LinkedHashSet<>();
            if (dept_head.getPermissions() != null) merged.addAll(dept_head.getPermissions());
            merged.addAll(deptHeadPerms);
            dept_head.setPermissions(new ArrayList<>(merged));
            roleRepo.save(dept_head);
        }



        // PROJECT_HEAD ROLE
        List<String> projectHeadPerms = Arrays.asList(
                Permission.DEPT_READALL.name(),
                Permission.PROJECT_READ.name()

                );

        Role project_head = roleRepo.findByName("PROJECT_HEAD");
        if (project_head == null) {
            project_head = new Role("PROJECT_HEAD", new ArrayList<>(projectHeadPerms));
            roleRepo.save(project_head);
        } else {
            Set<String> merged = new LinkedHashSet<>();
            if (project_head.getPermissions() != null) merged.addAll(project_head.getPermissions());
            merged.addAll(projectHeadPerms);
            project_head.setPermissions(new ArrayList<>(merged));
            roleRepo.save(project_head);
        }


    }

}
