package com.sprint.springexam.service;

import com.sprint.springexam.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service    // 직접 우리가 @Configuration + @Bean 통해 Bean 객체를 생성해 줄 것이기에 주석처리
public class UserService {
    private static final List<User> USERS = new ArrayList<>(){
        {
            add(new User(1, "Aaron", 10, "Developer", "Backend"));
            add(new User(2, "Baron", 20, "Developer", "Frontend"));
            add(new User(3, "Caron", 30, "Designer", "Design System"));
        }
    };

    public List<User> findAll() {
        return USERS;
    }

    public User findById(int id) {
        for (User each : USERS) {
            if (each.getId() == id) {
                return each;
            }
        }

        throw new RuntimeException("찾으시는 User가 존재하지 않습니다. - id : " + id);
    }
}
