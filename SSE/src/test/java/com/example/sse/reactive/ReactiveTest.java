package com.example.sse.reactive;

import com.example.sse.testCase.service.ReactiveService0;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReactiveTest {

    @Autowired
    private ReactiveService0 reactiveService;

    @Test
    public void testFluxByReactive(){
        reactiveService.fluxCase();
    }

    @Test
    public void testColdOrHotSeq(){
        reactiveService.coldOrHotSeq();
    }

    @Test
    public void testSubscription(){
        reactiveService.subscription();
    }
}
