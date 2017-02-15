package com.littleworld.todo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.littleworld.todo.model.Todo;

@Service
public class TodoService {

    List<Todo> todos = new ArrayList<>();

    public Todo createTodo(Todo todo ) {
        todos.add(todo);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }
 
    
    public Todo getTodoById(int id) {
        return todos.get(id);
    }
}

