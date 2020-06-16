package com.codecool.todospring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
