package com.example.jwt_project.repo;

import com.example.jwt_project.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepo extends MongoRepository<Todo,String> {

}
