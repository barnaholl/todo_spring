package com.codecool.todospring.controller;

import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping()
public class TodoController {

    TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/list")
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

   @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Todo> getList() {
       return todoRepository.findAll();

   }

}
