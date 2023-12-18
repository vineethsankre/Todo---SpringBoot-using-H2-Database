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
                new TodoRowMapper(), todo.getTodo(), todo.getPriority(), todo.getStatus());
        return savedTodo;
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("UPDATE TODOLIST SET todo = ? WHERE id = ?", todo.getTodo(), id);
        }
        if (todo.getPriority() != null) {
            db.update("UPDATE TODOLIST SET priority = ? WHERE id = ?", todo.getPriority(), id);
        }
        if (todo.getStatus() != null) {
            db.update("UPDATE TODOLIST SET staus = ? WHERE id = ?", todo.getStatus(), id);
        }

        return getTodoById(id);
    }

}
