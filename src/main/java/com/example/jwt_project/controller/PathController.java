package com.example.jwt_project.controller;

import com.example.jwt_project.model.Path;
import com.example.jwt_project.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paths")
public class PathController {

    @Autowired
    private PathService pathService;

    // Create or update a path
    @PostMapping
    public ResponseEntity<Path> createPath(@RequestBody Path path) {
        return ResponseEntity.ok(pathService.savePath(path));
    }

    //  Get all paths for a project
    @GetMapping("/{projectId}")
    public ResponseEntity<List<Path>> getAllPaths(@PathVariable String projectId) {
        return ResponseEntity.ok(pathService.getAllPathsByProject(projectId));
    }

    //  Get all paths by type (total/completed)
    @GetMapping("/{projectId}/type/{pathType}")
    public ResponseEntity<List<Path>> getPathsByType(@PathVariable String projectId, @PathVariable String pathType) {
        return ResponseEntity.ok(pathService.getPathsByType(projectId, pathType));
    }

    //  Delete path
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePath(@PathVariable String id) {
        pathService.deletePath(id);
        return ResponseEntity.noContent().build();
    }
}
