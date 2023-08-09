package com.example.sse.api;

import com.example.sse.dto.TestRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class SSETestAPI {

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> sseStream() {
        return Flux.just("SSE Connection")
                .map(sequence -> ServerSentEvent.builder(sequence).build())
                .doFinally(signalType -> {
                    if (signalType== SignalType.ON_COMPLETE){
                        System.out.println("Connection!");

                    }else if (signalType==SignalType.CANCEL){
                        System.out.println("Cancle!");
                    }
                });
    }

    /*@PostMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void sseStream(@RequestBody TestRequestDTO testRequestDTO) {
        return Flux.just("SSE Connection")
                .map(sequence -> ServerSentEvent.builder(sequence).build())
                .doFinally(signalType -> {
                    if (signalType== SignalType.ON_COMPLETE){
                        System.out.println("Connection!");

                    }else if (signalType==SignalType.CANCEL){
                        System.out.println("Cancle!");
                    }
                });
    }*/

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

    @GetMapping("/simple/generate")
    public Flux<ServerSentEvent<String>> streamDataToClient() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("custom-event") // 이벤트 이름 (클라이언트에서 사용)
                        .data("Data " + sequence) // 보낼 데이터
                        .build()
                );
    }
}
