package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/users")
public class UserApiController {
    private final UserService userService = new UserService();

    // @ResponseBody : Json 형식의 데이터 반환
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> users() {
        List<User> users = userService.findAll();

        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public User user() {
        User user = userService.findById(1);

        return user;
    }
}
