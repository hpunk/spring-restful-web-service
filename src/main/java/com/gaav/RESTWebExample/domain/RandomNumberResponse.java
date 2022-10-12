package com.gaav.RESTWebExample.domain;

import lombok.Data;

@Data
public class RandomNumberResponse {
    private Integer max;
    private Double randomNumber;
}
