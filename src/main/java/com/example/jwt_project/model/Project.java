package com.example.jwt_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    private String departmentId;
    private String name;
    private String description;
    private String projectHeadId;
    private List<String> projectMembers;


    public Project() {}

    public Project(String id, String departmentId, String name, String description, String projectHeadId, List<String> projectMembers) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
        this.projectHeadId = projectHeadId;
        this.projectMembers = projectMembers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectHeadId() {
        return projectHeadId;
    }

    public void setProjectHeadId(String projectHeadId) {
        this.projectHeadId = projectHeadId;
    }

    public List<String> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<String> projectMembers) {
        this.projectMembers = projectMembers;
    }
}
