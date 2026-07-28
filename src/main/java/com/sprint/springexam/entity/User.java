package com.sprint.springexam.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final int id;
    private String name;
    private int age;
    private String job;
    private String specialty;

    public User(int id, String name, int age, String job, String specialty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
        this.specialty = specialty;
    }
}
