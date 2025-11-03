package com.example.jwt_project.controller;

import com.example.jwt_project.model.Todo;
import com.example.jwt_project.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('PERM_TODOS_READ')")
    public List<Todo> getTodos() {
        return todoRepo.findAll();
    }

    @PostMapping("/todos")
    @PreAuthorize("hasAuthority('PERM_TODOS_CREATE')")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }
}
