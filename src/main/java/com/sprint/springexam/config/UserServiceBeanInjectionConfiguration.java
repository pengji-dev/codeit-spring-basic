package com.sprint.springexam.config;

import com.sprint.springexam.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceBeanInjectionConfiguration {
    @Bean
    public UserService userServiceInjection() {
        // - 지금의 예시는 구체 클래스지만 만약 인터페이스라면 상황에 따라 다른 구체 클래스 객체를 주입할 때
        // - 생성자 파라미터를 여러분들이 개별 설정한 어떤 값을 넣기 위해서
        return new UserService();
    }
}
