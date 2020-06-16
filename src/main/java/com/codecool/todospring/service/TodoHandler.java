package com.codecool.todospring.service;

import com.codecool.todospring.entity.Status;
import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoHandler {

    @Autowired
    TodoRepository todoRepository;

    public void addTodo(String title){
        Todo todo=Todo.builder().title(title).status(Status.ACTIVE).build();
        todoRepository.saveAndFlush(todo);
    }


}
