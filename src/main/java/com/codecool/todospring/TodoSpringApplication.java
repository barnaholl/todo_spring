package com.codecool.todospring;

import com.codecool.todospring.entity.Status;
import com.codecool.todospring.entity.Todo;
import com.codecool.todospring.entity.TodoUser;
import com.codecool.todospring.repository.TodoRepository;
import com.codecool.todospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TodoSpringApplication {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

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

            userRepository.save(TodoUser.builder()
                    .username("user")
                    .password(passwordEncoder.encode("password"))
                    .roles(Arrays.asList("ROLE_USER"))
                    .build()
            );

            userRepository.save(TodoUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                    .build()
            );

        };
    }
}
