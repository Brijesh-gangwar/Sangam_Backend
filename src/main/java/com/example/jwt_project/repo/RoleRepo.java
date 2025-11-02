package com.example.jwt_project.repo;

import com.example.jwt_project.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role,String> {
    Role findByName(String name);
}
