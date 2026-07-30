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
import java.util.Map;

/*
 *      페이지 응답 : 동적 페이지 생성을 위해 Model + ViewTemplate 두 개를 반환하는 방법
 *      1. 구현 클래스 ModelAndView - 고대(Spring 초기)
 *          - ViewTemplate + Model 합쳐서 반환
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserPageController {
    private final Map<String, IUserService> userServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView users(ModelAndView modelAndView) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        List<User> users = userServices.get("alphaTeamUserService").findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/list");

        return modelAndView;
    }

    // 한 명의 유저를 볼 수 있는 페이지 반환
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public ModelAndView user(ModelAndView modelAndView) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        User user = userServices.get("alphaTeamUserService").findById(1);
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("age", user.getAge());
        modelAndView.addObject("job", user.getJob());
        modelAndView.addObject("specialty", user.getSpecialty());
        modelAndView.setViewName("users/detail");

        return modelAndView;
    }
}
