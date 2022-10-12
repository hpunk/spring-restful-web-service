package com.gaav.RESTWebExample.domain;

import lombok.Data;

@Data
public class RandomWordResponse {
    private Integer maximumSize;
    private String word;
}
