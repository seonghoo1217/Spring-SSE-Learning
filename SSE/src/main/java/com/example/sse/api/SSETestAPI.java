package com.example.sse.api;

import com.example.sse.domain.User;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class SSETestAPI {
    @GetMapping("/simple/test")
    public Flux<ServerSentEvent<String>> getHealthCheck(){
        Mono<String> health1 = Mono.just("현재시간:" + LocalDateTime.now() + "입니다");
        Mono<String> health2 = Mono.just("핑퐁!");
        Flux<ServerSentEvent<String>> responseStream = Flux.concat(
                health1.map(health -> ServerSentEvent.<String>builder().data(health).build()),
                health2.delayElement(Duration.ofSeconds(5)).map(health -> ServerSentEvent.<String>builder().data(health).build())
        );

        return responseStream;
    }
}
