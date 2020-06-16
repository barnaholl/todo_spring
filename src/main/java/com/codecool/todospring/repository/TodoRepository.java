package com.codecool.todospring.repository;

import com.codecool.todospring.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

    List<Todo> findAll();

    Todo findById(int id);


}
