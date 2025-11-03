package com.example.jwt_project.controller;


import com.example.jwt_project.model.Department;
import com.example.jwt_project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.classfile.ClassFile;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;


    @GetMapping("/")
    @PreAuthorize("hasAuthority('PERM_DEPT_READALL')")
    public ResponseEntity<List<Department>> getAllDepartments() {

        List<Department> deptList = departmentService.getAllDepts();

        if (deptList.isEmpty()) {
            return ResponseEntity.status(404).body(null);
            // or better: ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deptList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_DEPT_READBYID')")
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        try {
            Department dept = departmentService.getById(id);
            return ResponseEntity.ok(dept);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }



    @PostMapping("/")
    @PreAuthorize("hasAuthority('PERM_DEPT_CREATE')")
    public ResponseEntity<?> createDept(@RequestBody Department department) {
        try {
            Department savedDept = departmentService.createDept(department);
            return ResponseEntity.status(201).body(savedDept);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Department not created: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERM_DEPT_DELETE')")
    public ResponseEntity<String> deleteDept(@PathVariable String id){

        try {
            departmentService.deleteDept(id);

            return ResponseEntity.status(200).body("Department deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("error : " + e.getMessage());
        }

    }



}
