package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *      Bean "사용"방법 3가지 - 이 중 현업에서는 생성자 방식만 씀
 *      1. 생성자 주입
 *          - 안전 : 현업에서 선호하는 방식
 *              - 1번째 안전 - 객체 고정 : final 필드 정의가 가능하여 런타임 중 (자바 어플리케이션 구동 중) 주입하는 객체가 달라지지 않도록 방어
 *              - 2번째 안전 - 순환 방지 : A 클래스에서 B 클래스를 주입받고 B 클래스가 A 클래스를 주입받으려하면 생성자 자체에서 = 컴파일 레벨에서 오류를 발생
 *      2. 수정자 주입
 *          - 위험 - 객체 고정 : final 필드 정의가 불가능하다는 점에서 이미 현업에서 사용할 이유가 없음
 *      3. 필드 주입
 *          - 위험 - 객체 고정 : final 필드 정의가 불가능하다는 점에서 이미 현업에서 사용할 이유가 없음
 */

@Slf4j
// 1. 생성자 주입
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {
    private final UserService userService;

//    @RequiredArgsConstructor 로 대신함
//    public UserApiController(UserService userService) {
//        this.userService = userService;
//    }

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
