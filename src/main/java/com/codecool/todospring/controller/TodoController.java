package com.codecool.todospring.controller;

import com.codecool.todospring.entity.Status;
import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.repository.TodoRepository;
import com.codecool.todospring.service.TodoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class TodoController {

    TodoRepository todoRepository;
    TodoHandler todoHandler;

    private static final String SUCCESS = "{\"success\":true}";

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

   @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Todo> getList() {
       return todoRepository.findAll();
   }

   @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo-title") String title) {
        Todo todo=Todo.builder().title(title).status(Status.ACTIVE).build();
        todoRepository.save(todo);
        //todoHandler.addTodo(title);
        return SUCCESS;
   }


}
