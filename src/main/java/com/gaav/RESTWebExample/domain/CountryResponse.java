package com.gaav.RESTWebExample.domain;

import lombok.Data;

import java.util.List;

@Data
public class CountryResponse {
    public String searchString;
    public List<String> capitals;
}
