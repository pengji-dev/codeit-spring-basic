package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
//@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserPageController {
    private final IUserService userService;

    // IUserService 인터페이스가 @Qulifier로 Bean 명칭을 "userService"라고 지정하고 여러 구체 클래스 중에 @Qulifier("userService")라고 선언한 구체 클래스만 사용할거라고 지정함.
    public UserPageController(@Qualifier("userService") IUserService userService) {
        this.userService = userService;
    }

    // 전체 유저를 볼 수 있는 페이지 반환
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();

        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/list");

        return modelAndView;
    }

    // 한 명의 유저를 볼 수 있는 페이지 반환
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findById(1);
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("age", user.getAge());
        modelAndView.addObject("job", user.getJob());
        modelAndView.addObject("specialty", user.getSpecialty());
        modelAndView.setViewName("users/detail");

        return modelAndView;

    }
}
