package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

@RestController
public class TodoController {
    @Autowired
    TodoH2Service todoH2Service;

    @GetMapping("/todos")
    public ArrayList<Todo> addTodo() {
        return todoH2Service.addTodo();
    }
}
