package com.codewithsudhakaran.todo.controller;

import com.codewithsudhakaran.todo.model.Todo;
import com.codewithsudhakaran.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todo/all")
    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    @GetMapping("/todo/{id}")
    public Todo getById(@PathVariable int id){
        return todoRepository.findById(id);
    }
    @PostMapping("/todo")
    public List<Todo> insertTodo(@RequestBody Todo todo){
        return todoRepository.insert(todo);
    }
    @PutMapping("/todo")
    public List<Todo> updatetodo(@RequestBody Todo todo){
        return todoRepository.update(todo);
    }
    @DeleteMapping("/todo/{id}")
    public List<Todo> deleteTodo(@PathVariable int id){
        return todoRepository.delete(id);
    }

}
