package com.sprint.springexam.controller;

import com.sprint.springexam.entity.User;
import com.sprint.springexam.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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
 *
 *      Bean 주입받는 필드 타입 기준 3가지
 *      구체 클래스를 정의하는 방법 3가지와 동일하다
 *      1. 구체 클래스 <- 온전히 그 자체만으로 존재하는 독립적 구체 클래스
 *      2. 구체 클래스 <- 인터페이스의 구현
 *      3. 구체 클래스 <- 부모 클래스의 상속
 *
 *      Bean 정의 - 사용 시 주의
 *      Bean 주입받을 때 그리고 Bean 정의할 때 명칭에 주의하자!
 *      - Bean 주입받을 때 클래스명 기준 : IUserService alphaTeamUserService 필드 <- class AlphaTeamUserService
 *      - Bean 주입받을 때 개별명칭 기준 : IUserService userService 필드 <- @Qualifier("userService") class AlphaTeamUserService
 *
 *      단일 인터페이스에 대한 다수 구체 클래스가 있을 시 Bean 객체 주입 시 충돌
 *      단일 인터페이스에 대한 다수 구체 클래스가 있을 때 어떤 Bean 객체를 주입해야 할지 Spring Container는 알 수 없어서 우리가 명시해줘야 한다.
 *
 *      충돌 해결을 위한 방법 2가지
 *      - @Qualifier = 수많은 충돌나는 구체 클래스들 중 Bean 명칭을 일치시켜 지정
 *          - Spring 3 버전부터 생성자 파라미터 이름 기반의 Bean 매핑 기능이 더 이상 동작되지 않는다고 한다. - Deprecated
 *              - 그래서 Lombok 생성자가 아닌 직접 생성자를 명시해줘야 한다.
 *      - @Primary = 수많은 충돌나는 구체 클래스들 중 이것을 Bean 객체로 사용해주세요~ 라고 직접 지정
 *
 *      * 궁금증
 *      1. @Qualifier와 @Primary 중 각기 다른 IUserService 인터페이스의 구체 클래스에 각각 적용 시 우선순위는 무엇일까?
 *          - 우선순위는 @Qualifier가 높다! 그 이유는 개발자가 직접 Bean 주입을 위해 Bean 명칭을 지정하여 특정 구체 클래스에 지정하기 때문이다.
 *      2. @Qualifier와 @Primary를 동시에 사용하는 경우가 현업에서 있을까?
 *          - 많지는 않지만 있다! DB에 접근하기 위해서는 Repository를 거치는데 테스트를 위해서 DB까지 접근하기는 오래 걸리기에 테스트용을
 *            Map을 선언하여 해당 Map에 우선권을 주기 위해 @Qualifier를 선언하고, Repository에는 @Primaey를 준다.
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {
    private final List<IUserService> userServices;

    // @ResponseBody : Json 형식의 데이터 반환
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> users() {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        List<User> users = userServices.get(0).findAll();

        return users;

//        List<User> users = userService.findAll();
//
//        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public User user() {
        log.info("단일 IUserService 인터페이스의 구현체들은 Spring Container 농부가 한번에 모아 JCF 내 주입 : {}", userServices);

        User user = userServices.get(0).findById(1);

        return user;

//        User user = userService.findById(1);
//
//        return user;
    }
}
