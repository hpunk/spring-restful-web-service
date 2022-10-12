package com.gaav.RESTWebExample.domain.restcountries;

import lombok.Data;

import java.util.List;

@Data
public class CountryData {
    private List<String> capital;
    private CountryName name;

    @Data
    public static class CountryName {
        private String common;
    }
}
