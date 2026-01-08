package com.example.jwt_project.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "departments")
public class Department {

    @Id
    private String id;

    private String name;
    private String location;
    private String departmentHeadId;


    private List<String> projectList;

    private List<String> departmentMembers;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(String departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDepartmentMembers() {
        return departmentMembers;
    }

    public void setDepartmentMembers(List<String> departmentMembers) {
        this.departmentMembers = departmentMembers;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    // all args constructors
    public Department(String id, String name, String location, String departmentHeadId, List<String> projectList, List<String> departmentMembers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.departmentHeadId = departmentHeadId;
        this.projectList = projectList;
        this.departmentMembers = departmentMembers;
    }

    // no args constructor
    public Department() {
    }
}
