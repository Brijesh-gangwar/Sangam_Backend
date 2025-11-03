package com.example.jwt_project.service;

import com.example.jwt_project.model.Department;
import com.example.jwt_project.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {


    @Autowired
    DepartmentRepo departmentRepo;


    public List<Department> getAllDepts(){
        return departmentRepo.findAll();
    }


    public Department getById(String id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }


    public Department createDept(Department department){
        return departmentRepo.save(department);
    }


    public void deleteDept(String id) {

        if (!departmentRepo.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }

        departmentRepo.deleteById(id);
    }



}
