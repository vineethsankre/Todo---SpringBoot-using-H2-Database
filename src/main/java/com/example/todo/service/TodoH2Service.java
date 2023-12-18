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

}
