package com.sprint.springexam.service;

import com.sprint.springexam.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(int id);
}
