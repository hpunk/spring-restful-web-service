package com.gaav.RESTWebExample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RandomWordGeneratorService {
    private static Integer timesCalled = 1;
    private final Integer MAX_LETTER_NUMBER = 25;
    private final Integer Z_INDEX_NUMBER = 90;

    public RandomWordGeneratorService () {
        log.info("RandomWordGeneratorService instantiated for {} time ...",timesCalled++);
    }

    public String generateRandomWord(Integer length) {
        String word = "";
        for(int i = 0; i<length; i++) {
            word+= generateRandomLetter();
        }
        return word;
    }

    private char generateRandomLetter(){
        return (char) (Z_INDEX_NUMBER - Double.valueOf(Math.random()*MAX_LETTER_NUMBER).intValue());
    }
}
