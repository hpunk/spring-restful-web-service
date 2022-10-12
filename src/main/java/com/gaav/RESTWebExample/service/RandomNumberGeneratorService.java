package com.gaav.RESTWebExample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RandomNumberGeneratorService {
    private static Integer timesCalled = 1;

    public RandomNumberGeneratorService(){
        log.info("RandomNumberGeneratorService instantiated for {} time ...",timesCalled++);
    }

    public double generateRandomNumber(Integer max){
        return Math.random()*max;
    }
}
