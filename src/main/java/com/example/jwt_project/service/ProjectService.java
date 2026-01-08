package com.example.jwt_project.service;

import com.example.jwt_project.model.Project;
import com.example.jwt_project.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    //  Create or Update Project
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    //  Get all projects
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    //  Get project by ID
    public Optional<Project> getProjectById(String id) {
        return projectRepo.findById(id);
    }

    //  Update existing project
    public Optional<Project> updateProject(String id, Project updatedProject) {
        return projectRepo.findById(id).map(project -> {
            project.setDepartmentId(updatedProject.getDepartmentId());
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setProjectHeadId(updatedProject.getProjectHeadId());
            project.setProjectMembers(updatedProject.getProjectMembers());
            return projectRepo.save(project);
        });
    }

    // ✅ Delete project by ID
    public boolean deleteProject(String id) {
        if (projectRepo.existsById(id)) {
            projectRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
