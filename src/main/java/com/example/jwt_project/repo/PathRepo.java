package com.example.jwt_project.repo;

import com.example.jwt_project.model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathRepo extends MongoRepository<Path,String> {

    List<Path> findByProjectId(String projectId);

     List<Path> findByProjectIdAndPathType(String projectId, String pathType);
}
