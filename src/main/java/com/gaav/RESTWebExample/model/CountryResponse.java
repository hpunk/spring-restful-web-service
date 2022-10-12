package com.gaav.RESTWebExample.model;

import lombok.Data;

import java.util.List;

@Data
public class CountryResponse {
    public String searchString;
    public List<String> capitals;
}
