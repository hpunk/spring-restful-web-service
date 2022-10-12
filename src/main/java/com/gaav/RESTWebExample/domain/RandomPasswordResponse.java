package com.gaav.RESTWebExample.domain;

import lombok.Data;

@Data
public class RandomPasswordResponse {
    private String rules;
    private String password;
}
