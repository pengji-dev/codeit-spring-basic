package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 *      단일 인터페이스 기반의 다수 구현 클래스 Bean 객체들을 한번에 관리하기
 *      - 이 모두를 JCF Collection 통해 한번에 받아다가 쓸 수 있도록 Spring Container가 배려해준다.
 *      1. List<IUserService> 자료구조
 *          - 구현 클래스 3개가 자동으로 Spring Container 통해 주입된다. - 실제로 로그를 찍어보면 확인 가능
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserPageController {
    private final List<IUserService> userServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String users(Model model) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        IUserService userService = this.userServices.get(0);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "users/list";

//        ModelAndView modelAndView = new ModelAndView();
//
//        List<User> users = userService.findAll();
//        modelAndView.addObject("users", users);
//        modelAndView.setViewName("users/list");
//
//        return modelAndView;
    }

    // 한 명의 유저를 볼 수 있는 페이지 반환
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String user(Model model) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        IUserService userService = userServices.get(0);
        User user = userService.findById(1);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());

        return "users/detail";

//        ModelAndView modelAndView = new ModelAndView();
//
//        User user = userService.findById(1);
//        modelAndView.addObject("id", user.getId());
//        modelAndView.addObject("name", user.getName());
//        modelAndView.addObject("age", user.getAge());
//        modelAndView.addObject("job", user.getJob());
//        modelAndView.addObject("specialty", user.getSpecialty());
//        modelAndView.setViewName("users/detail");
//
//        return modelAndView;

    }
}
