package com.example.sse.service;

import com.example.sse.domain.User;
import com.example.sse.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

}
