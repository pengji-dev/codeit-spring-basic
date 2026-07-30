package com.sprint.springexam.service;

import com.sprint.springexam.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// IUserService 인터페이스 구현체 중 2번째 : BetaTeamUserService
//@Qualifier("userService")
//@Primary
public class BetaTeamUserService implements IUserService {
    private static final List<User> USERS = new ArrayList<>(){
        {
            add(new User(4, "Daron", 10, "Developer", "Backend"));
            add(new User(5, "Earon", 20, "Developer", "Frontend"));
            add(new User(6, "Faron", 30, "Designer", "Design System"));
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
