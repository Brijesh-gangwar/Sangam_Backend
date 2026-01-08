package com.example.jwt_project.controller;

import com.example.jwt_project.model.Project;
import com.example.jwt_project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //  Get all projects
    @GetMapping("/")
    @PreAuthorize("hasAuthority('PERM_PROJECT_READ')")
    public ResponseEntity<?> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();

        if (projectList.isEmpty()) {
            return ResponseEntity.status(404).body("No projects found");
        }

        return ResponseEntity.ok(projectList);
    }

    //  Get project by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_PROJECT_READ')")
    public ResponseEntity<?> getProjectById(@PathVariable String id) {
        try {
            return projectService.getProjectById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(404).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    //  Create new project
    @PostMapping("/")
    @PreAuthorize("hasAuthority('PERM_PROJECT_CREATE')")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project savedProject = projectService.saveProject(project);
            return ResponseEntity.status(201).body(savedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Project not created: " + e.getMessage());
        }
    }

    //  Update project by ID
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_PROJECT_UPDATE')")
    public ResponseEntity<?> updateProject(@PathVariable String id, @RequestBody Project updatedProject) {
        try {
            return projectService.updateProject(id, updatedProject)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(404).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error updating project: " + e.getMessage());
        }
    }

    //  Delete project by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_PROJECT_DELETE')")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        try {
            boolean deleted = projectService.deleteProject(id);
            if (deleted) {
                return ResponseEntity.status(200).body("Project deleted successfully");
            } else {
                return ResponseEntity.status(404).body("Project not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error deleting project: " + e.getMessage());
        }
    }
}
