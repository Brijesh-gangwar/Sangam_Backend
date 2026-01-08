package com.example.jwt_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "paths")
public class Path {

    @Id
    private String id;

    private String projectId;     //  Link to Project
    private String pathName;
    private double distance;
    private String pathType;      //  "total" or "completed"

    private String status;
    private List<Position> positions;

    public Path() {}

    public Path(String id, String projectId, String pathName, String pathType, double distance, String status, List<Position> positions) {
        this.id = id;
        this.projectId = projectId;
        this.pathName = pathName;
        this.pathType = pathType;
        this.distance = distance;
        this.status = status;
        this.positions = positions;
    }

    // ✅ Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
