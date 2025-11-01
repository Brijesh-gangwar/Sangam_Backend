package com.example.jwt_project.repo;

import com.example.jwt_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

Users findByUsername(String username);

}
