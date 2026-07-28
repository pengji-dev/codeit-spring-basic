package com.sprint.springexam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TestController {
    @GetMapping("/errorTest")
    public String errorTest() {
        throw new RuntimeException("강제 500 에러 발생");
    }
}
