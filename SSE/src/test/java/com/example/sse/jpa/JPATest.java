package com.example.sse.jpa;

import com.example.sse.domain.User;
import com.example.sse.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPATest {

    @Autowired
    private UserService userService;
    @Test
    public void createUser(){
        User dummy1 = User.builder()
                .email("dummy1@email")
                .username("dummy1")
                .age(25)
                .build();

        User dummy2 = User.builder()
                .email("dummy2@email")
                .username("dummy2")
                .age(20)
                .build();

        User dummy3 = User.builder()
                .email("dumm3y@email")
                .username("dummy3")
                .age(21)
                .build();

        User dummy4 = User.builder()
                .email("dummy4@email")
                .username("dummy4")
                .age(28)
                .build();
        userService.createUser(dummy1);
        userService.createUser(dummy2);
        userService.createUser(dummy3);
        userService.createUser(dummy4);
    }
}
