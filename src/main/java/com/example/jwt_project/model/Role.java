package com.example.jwt_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private String name; // e.g., ADMIN or USER

    private List<String> permissions; // e.g., ["STUDENTS_READ","STUDENTS_WRITE"]

    public Role() {}

    public Role(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

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

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
