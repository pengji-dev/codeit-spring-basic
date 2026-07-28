package com.sprint.springexam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        log.info(" - IndexController 내부에 정의한 @RequestMapping 통해 페이지 반환");
        log.info(" - HandlerMapping 우선순위에 따라 3순위인 WelcomePageHandlerMapping까지 가지 않고 1순위인 RequestMappingHandlerMapping에서 처리");

        return "index";
    }
}
