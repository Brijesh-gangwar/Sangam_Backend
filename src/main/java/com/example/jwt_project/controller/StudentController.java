package com.example.jwt_project.controller;

import com.example.jwt_project.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

      List<Student> students = new ArrayList<>(List.of(
            new Student(1,"brij",60),
            new Student(2,"gang",70)
    ));

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('PERM_STUDENTS_READ')")
    public List<Student> getstudent(){
        return  students;

    }

    @GetMapping("/token")
    public CsrfToken gettoken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    @PreAuthorize("hasAuthority('PERM_STUDENTS_WRITE')")
    public  Student addStudent(@RequestBody Student student){
        students.add(student);
        return student ;
    }
}
