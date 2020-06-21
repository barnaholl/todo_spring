package com.codecool.todospring.controller;

import com.codecool.todospring.entity.Status;
import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class TodoController {

    TodoRepository todoRepository;

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
        return SUCCESS;
   }
    @DeleteMapping("/todos/completed")
    public String removeCompleted() {
        List<Todo> todos= todoRepository.findAll();
        for (Todo todo : todos) {
            if(todo.getStatus()==Status.COMPLETE){
                todoRepository.delete(todo);
            }
        }
        return SUCCESS;
    }

    @PutMapping("/todos/toggle_all")
    public String toggleAll(){
        List<Todo> todos= todoRepository.findAll();
        for (Todo todo : todos) {
            if(todo.getStatus()==Status.ACTIVE){
                todo.setStatus(Status.COMPLETE);
            }
            else{
                todo.setStatus(Status.ACTIVE);
            }
            todoRepository.save(todo);
        }
        return SUCCESS;

    }
    @DeleteMapping("/todos/{id}")
    public String removeById(@PathVariable int id) {
        todoRepository.delete(todoRepository.findById(id));
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("todo-title") String title) {
        Todo todo=todoRepository.findById(id);
        todo.setTitle(title);
        todoRepository.save(todo);
        return SUCCESS;
    }
    @GetMapping("/todos/{id}")
    public String findById(@PathVariable int id){
        todoRepository.findById(id);
        return SUCCESS;
    }

    @PutMapping("/todos/{id}/toggle_status")
    public String toggleById(@PathVariable("id") int id) {
        Todo todo=todoRepository.findById(id);
        if(todo.getStatus()==Status.ACTIVE){
            todo.setStatus(Status.COMPLETE);
        }
        else{
            todo.setStatus(Status.ACTIVE);
        }
        todoRepository.save(todo);
        return SUCCESS;
    }




}
