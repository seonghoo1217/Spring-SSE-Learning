package com.example.sse.testCase.service;

import com.example.sse.domain.User;
import com.example.sse.handler.exception.UserNotFoundException;
import com.example.sse.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveService {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUserToFlux(){
        return userRepository.findAll();
    }

    public Mono<User> getUserToMonoById(Long id){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User Not Found By Id:"+id)));
    }

    public Flux<User> getUserToFluxByAgeCondition(Integer age){
        return userRepository.findAll()
                .filter(user -> user.getAge()>=age)
                .collectList()
                .flatMapMany(Flux::fromIterable);
    }

    public Mono<User> updateUserInfo(Long id,String email){
        return userRepository.findById(id)
                .flatMap(exist->{
                    exist.updateEmail(email);
                    return userRepository.save(exist);
                })
                .switchIfEmpty(Mono.error(new UserNotFoundException("User Not Found By Id:"+id)));
    }
}
