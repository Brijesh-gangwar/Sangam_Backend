package com.example.jwt_project.repo;

import com.example.jwt_project.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<Users,String> {

	Users findByUsername(String username);

}