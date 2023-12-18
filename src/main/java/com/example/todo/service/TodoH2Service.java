package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.*;

@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    JdbcTemplate db;

    @Override
    public ArrayList<Todo> addTodo() {
        List<Todo> todoList = db.query("SELECT * FROM TODOLIST", new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(todoList);
        return todos;
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            Todo todo = db.queryForObject("SELECT * FROM TODOLIST WHERE id = ?", new TodoRowMapper(), id);
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update("INSERT INTO TODOLIST(todo, priority, status) values (?,?,?)",
                todo.getTodo(), todo.getPriority(), todo.getStatus());
        Todo savedTodo = db.queryForObject(
                "SELECT * FROM TODOLIST WHERE todo = ? and priority = ? and status = ?",
                new todo.getTodo() , todo.getPriority(), todo.getStatus());
        return savedTodo;
    }

}
