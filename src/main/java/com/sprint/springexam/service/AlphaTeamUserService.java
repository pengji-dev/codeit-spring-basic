package com.sprint.springexam.service;

import com.sprint.springexam.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("userService")       // IUserService 인터페이스의 여러 구체 클래스 중 해당 구체 클래스를 사용하도록 @Qulifier("userService")를 사용해서 지목한다.
// IUserService 인터페이스 구현체 중 1번째 : AlphaTeamUserService
public class AlphaTeamUserService implements IUserService {
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
