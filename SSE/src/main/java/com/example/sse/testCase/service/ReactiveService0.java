package com.example.sse.testCase.service;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReactiveService0 {

    /*private final UserRepository userRepository;

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
    }*/

    public void fluxCase() {
        Flux.just(1,2,3)
                .doOnNext(i-> System.out.println("doOneNext: "+i))
                .subscribe(i-> System.out.println("Recived: "+i));

        Flux<Integer> seq = Flux.just(1, 2, 3)
                .doOnNext(i-> System.out.println("do:"+i));

        System.out.println("시퀀스 생성");
        seq.subscribe(i-> System.out.println("Recived:"+i));
    }

    public void coldOrHotSeq(){
        //Cold : 구독 시점부 데이터를 새로 생성한다. 앞선 Flex.just가 그 예. 구독이 일어나지 않으면 데이터를 생성하지 않음
        Flux<Integer> seq = Flux.just(1, 2, 3);

        seq.subscribe(v -> System.out.println("구독1: " + v)); // 구독

        seq.subscribe(v -> System.out.println("구독2: " + v)); // 구독

        //콜드 시퀀스는 마치 API호출과 같이 요청 한번당 하나의 응답을 만들어낸다.
        //핫 시퀀스는 구독 여부에 상관없이 데이터가 생성된다.
    }

    public void subscription(){
        /*onSubscribe(Subscription s): 구독을 하면 Publisher와 연동된 Subscription을 받는다. 전달받은 Subscription을 이용해서 Publisher에 데이터를 요청한다.
        onNext(T t): Publisher가 next 신호를 보내면 호출된다.
        onError(Throwable t): Publisher가 error 신호를 보내면 호출된다.
        onComplete(): Publisher가 complete 신호를 보내면 호출된다.*/

        Flux<Integer> seq = Flux.just(1, 2, 3);

        seq.subscribe(new Subscriber<Integer>() {

            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscriber.onSubscribe");
                this.subscription=s;
                this.subscription.request(1);//publisher 데이터 요청
//                this.subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Subscription.onNext:"+integer);
                this.subscription.request(1);//Publisher 데이터 요청
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error subs:"+t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Subs complete");
            }
        });
    }


}
