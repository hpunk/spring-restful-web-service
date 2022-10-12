package com.gaav.RESTWebExample.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class RandomPasswordGeneratorService {
    private static Integer timesCalled = 1;

    private final int CHAR_MAX_AMOUNT = 25;
    private final int NUMBER_MAX_AMOUNT = 10;
    private final int DETAULT_SPECIAL_MAX_AMOUNT = 5;
    private final String LOWER = "l";
    private final String UPPER = "u";
    private final String SPECIAL = "s";
    private final String DIGIT = "d";
    private final char[] VALID_LOWER_CASE_CHARS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private final char[] VALID_UPPER_CASE_CHARS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final char[] VALID_NUMBER_CHARS = {'0','1','2','3','4','5','6','7','8','9'};
    private final char[] DEFAULT_VALID_SPECIAL_CHARS = {'@','#','$','%','&'};

    private RandomPasswordGeneratorService() {
      log.info("RandomPasswordGeneratorService instantiated for the {} time ...",timesCalled++);
    }

    public String generateRandomPassword(Integer minLowerCase, Integer minUpperCase, Integer minDigit, Integer minSpecial){
        Integer totalChars = minLowerCase+minUpperCase+minDigit+minSpecial;
        LinkedList<String> charsType = new LinkedList<>();
        fillCharTypeList(charsType,minLowerCase,List.of(LOWER));
        fillCharTypeList(charsType,minUpperCase,List.of(UPPER));
        fillCharTypeList(charsType,minDigit,List.of(DIGIT));
        fillCharTypeList(charsType,minSpecial,List.of(SPECIAL));
        String password = "";
        for(int i=0; i<totalChars; i++){
            val index = getRandomNumber(charsType.size()-1);
            charsType.get(index);
            switch (charsType.get(index)) {
                case LOWER:
                    password+=getRandomCharacter(VALID_LOWER_CASE_CHARS,CHAR_MAX_AMOUNT);
                    break;
                case UPPER:
                    password+=getRandomCharacter(VALID_UPPER_CASE_CHARS,CHAR_MAX_AMOUNT);
                    break;
                case SPECIAL:
                    password+=getRandomCharacter(DEFAULT_VALID_SPECIAL_CHARS,DETAULT_SPECIAL_MAX_AMOUNT);
                    break;
                case DIGIT:
                    password+=getRandomCharacter(VALID_NUMBER_CHARS,NUMBER_MAX_AMOUNT);
                    break;

            }
            charsType.remove(index);
        }
        return password;
    }

    /*public String generateRandomPassword(Integer minLetters, Integer minDigit, Integer minSpecial){

    }

    public String generateRandomPassword(Integer minNormal, Integer minSpecial){

    }

    public String generateRandomPassword(Integer minLenght){

    }*/

    private void fillCharTypeList(LinkedList<String> charsType, Integer times, List<String> values) {
        for(int i = 0; i<times; i++){
            charsType.add(values.get(getRandomNumber(values.size()-1)));
        }
    }

    private char getRandomCharacter(char[] validChars, int maxIndex) {
        return validChars[getRandomNumber(maxIndex)];
    }

    private int getRandomNumber(int maxValue) {
        return Double.valueOf(Math.random()*maxValue).intValue();
    }
}
