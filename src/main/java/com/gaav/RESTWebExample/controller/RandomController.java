package com.gaav.RESTWebExample.controller;

import com.gaav.RESTWebExample.domain.RandomNumberResponse;
import com.gaav.RESTWebExample.domain.RandomPasswordResponse;
import com.gaav.RESTWebExample.domain.RandomWordResponse;
import com.gaav.RESTWebExample.service.RandomNumberGeneratorService;
import com.gaav.RESTWebExample.service.RandomPasswordGeneratorService;
import com.gaav.RESTWebExample.service.RandomWordGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RandomController {

    @Autowired
    private RandomWordGeneratorService wordService;

    private final RandomPasswordGeneratorService passwordService;

    @GetMapping("/random/number")
    public RandomNumberResponse getRandomNumber(@RequestParam(value="max") Integer maximum) {
        //this is context, and a way of getting beans, but not practical at all, since all services get instantiated every time the following line is executed
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
                "com.gaav.RESTWebExample.service");
        RandomNumberGeneratorService service = context.getBean(RandomNumberGeneratorService.class);
        RandomNumberResponse response = new RandomNumberResponse();
        response.setMax(maximum);
        response.setRandomNumber(service.generateRandomNumber(maximum));
        context.close();
        return response;
    }

    @GetMapping("/random/word")
    public RandomWordResponse getRandomWord(@RequestParam(value="max") Integer maximum) {
        RandomWordResponse response = new RandomWordResponse();
        response.setMaximumSize(maximum);
        response.setWord(wordService.generateRandomWord(maximum));
        return response;
    }

    @GetMapping("/random/password")
    public RandomPasswordResponse getRandomPassword(@RequestParam(value="upper") Integer minUpper, @RequestParam(value="lower") Integer minLower, @RequestParam(value="special") Integer minSpecial, @RequestParam(value="digit") Integer minDigit){
        RandomPasswordResponse response = new RandomPasswordResponse();
        response.setPassword(passwordService.generateRandomPassword(minUpper, minLower, minDigit, minSpecial));
        response.setRules(String.format("Password generated with %s upper-case, %s lower-case, %s digits, %s special characters",minUpper,minLower,minDigit,minSpecial));
        return response;
    }
}
