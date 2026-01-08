package com.example.jwt_project.service;

import com.example.jwt_project.model.Path;
import com.example.jwt_project.repo.PathRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathService {

    @Autowired
    private PathRepo pathRepo;

    public List<Path> getAllPathsByProject(String projectId) {
        return pathRepo.findByProjectId(projectId);
    }

    public List<Path> getPathsByType(String projectId, String pathType) {
        return pathRepo.findByProjectIdAndPathType(projectId, pathType);
    }

    public Path savePath(Path path) {
        return pathRepo.save(path);
    }

    public void deletePath(String id) {
        pathRepo.deleteById(id);
    }
}
