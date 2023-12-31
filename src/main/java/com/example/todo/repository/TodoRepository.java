package com.example.todo.repository;

import com.example.todo.*;
import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository {
    ArrayList<Todo> addTodo();

    Todo getTodoById(int id);

    Todo addTodo(Todo todo);

    Todo updateTodo(int id, Todo todo);
    void deleteTodo(int id);

}