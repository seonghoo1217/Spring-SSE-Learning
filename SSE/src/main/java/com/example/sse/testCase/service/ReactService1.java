/*
package com.example.sse.testCase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.Random;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Service
public class ReactService1 {

    private Logger logger;

    //시퀀스를 직접적으로 생성할 일이 많지는 않지만 흐름을 익히는데는 유용하게 사용된다.

    public void seqFlow(){
        //해당 flux는 1,2,3을 순서대로 발행한 뒤 complete 신호를 발생시킨다. 다만 데이터가 없다면 complete만 진행
        Flux<Integer> seq= Flux.just(1,2,3);

        Flux<Integer> emptySeq = Flux.just();

        //Mono.just()도 동일한 결과를 나타내지만 인자는 1개이다.
        Mono<Integer> monoSeq = Mono.just(1);

        //Mono를 빈값으로 발행하기위해선 Mono.empty()를 사용한다 null을 넣을 경우 NullPointException
        Mono<Object> emptyMono = Mono.empty();

        //Range로 정수생성하기 첫인자값부터 두번째 인자값의 값까지 순차적으로 증가하는 시퀀스 생성
        Flux<Integer> range = Flux.range(11, 5);
    }

    public void fluxGenerate() {
        //Flux.generate 사용할 수도 있음
        */
/*generator는 Subscriber로부터 요청이 왔을 때 신호를 생성한다. generate()가 생성한 Flux는 다음과 같은 방식으로 신호를 발생한다.
        Subscriber의 요청에 대해 인자로 전달받은 generator를 실행한다. generator를 실행할 때 인자로 SynchronousSink를 전달한다.
        generator는 전달받은 SynchronousSink를 사용해서 next, complete, error 신호를 발생한다. 한 번에 1개의 next() 신호만 발생할 수 있다.*//*


        Consumer<SynchronousSink<Integer>> randGenerator = new Consumer<>() {
            int emitCount = 0;

            Random rand = new Random();

            @Override
            public void accept(SynchronousSink<Integer> sink) {
                int data = rand.nextInt(100) + 1; // 1~100 사이 임의 정수
                logger.info("Generator sink next " + data);
                sink.next(data); // 임의 정수 데이터 발생
                if (emitCount == 10) { // 10개 데이터를 발생했으면
                    logger.info("Generator sink complete");
                    sink.complete(); // 완료 신호 발생
                }
            }
        };

        Flux<Integer> seqGen = Flux.generate(randGenerator);

        seqGen.subscribe(new BaseSubscriber<>() {
            private int receiveCount = 0;

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                logger.info("Subscriber#onSubscribe");
                logger.info("Subscriber request first 3 items");
                request(3);
            }
        }

    };
}
*/
