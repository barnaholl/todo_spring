package com.codecool.todospring;

import com.codecool.todospring.entity.Status;
import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TodoSpringApplication {

    @Autowired
    TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoSpringApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Todo todo1 = Todo.builder().title("Todo 1").status(Status.ACTIVE).build();
            Todo todo2 = Todo.builder().title("Todo 2").status(Status.ACTIVE).build();
            Todo todo3 = Todo.builder().title("Todo 3").status(Status.ACTIVE).build();

            todoRepository.save(todo1);
            todoRepository.save(todo2);
            todoRepository.save(todo3);

            Todo todo=todoRepository.getById(1);
            todo.setStatus(Status.COMPLETE);
            todoRepository.save(todo);
        };
    }
}
