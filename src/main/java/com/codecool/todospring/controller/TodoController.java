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

    @PutMapping("/todos/toggle_all") //TODO:Check
    public String toggleAll(){
        List<Todo> todos= todoRepository.findAll();
        for (Todo todo : todos) {
            if(todo.getStatus()==Status.ACTIVE){
                todo.setStatus(Status.COMPLETE);
            }
            else{
                todo.setStatus(Status.ACTIVE);
            }
        }
        return SUCCESS;

    }
    @DeleteMapping("/todos/{id}")
    public String removeById(@PathVariable int id) {
        todoRepository.delete(todoRepository.findById(id));
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String update(@RequestParam("id") int id, @RequestParam("todo-title") String title) {
        todoRepository.findById(id).setTitle(title);
        return SUCCESS;
    }




}
