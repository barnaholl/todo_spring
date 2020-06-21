package com.codecool.todospring.repository;

import com.codecool.todospring.entity.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser,Integer> {

    Optional<TodoUser> findByUsername(String username);
}
