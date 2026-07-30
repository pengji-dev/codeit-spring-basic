package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/*
 *      페이지 응답 : 동적 페이지 생성을 위해 Model + ViewTemplate 두 개를 반환하는 방법
 *      1. 구현 클래스 ModelAndView - 고대(Spring 초기)
 *          - ViewTemplate + Model 합쳐서 반환
 *      2. 구현 클래스 ModelMap - 예전(Spring 중기)
 *          - ViewTemplate / Model 역할 분리
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserPageController {
    private final Map<String, IUserService> userServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String users(ModelMap modelMap) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        List<User> users = userServices.get("alphaTeamUserService").findAll();
        modelMap.addAttribute("users", users);

        return "users/list";
    }

    // 한 명의 유저를 볼 수 있는 페이지 반환
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String user(ModelMap modelMap) {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        User user = userServices.get("alphaTeamUserService").findById(1);
        modelMap.addAttribute("id", user.getId());
        modelMap.addAttribute("name", user.getName());
        modelMap.addAttribute("age", user.getAge());
        modelMap.addAttribute("job", user.getJob());
        modelMap.addAttribute("specialty", user.getSpecialty());

        return "users/detail";
    }
}
