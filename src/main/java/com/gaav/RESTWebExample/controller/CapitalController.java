package com.gaav.RESTWebExample.controller;

import com.gaav.RESTWebExample.model.CountryResponse;
import com.gaav.RESTWebExample.model.restcountries.CountryData;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.stream.Collectors;

@RestController
@Slf4j
public class CapitalController {
    private static final String countryApiUri = "https://restcountries.com/v3.1/name/";

    @GetMapping("/capital")
    public CountryResponse fetchCapitalForCountryName(@RequestParam(value="country") String countryName) {
        CountryData[] result = fetchDataFromAPI(countryName);
        val response = mapAPIResultToResponse(result);
        response.setSearchString(countryName);
        return response;
    }

    private CountryResponse mapAPIResultToResponse(CountryData[] result) {
        CountryResponse response = new CountryResponse();
        val resultList = ArrayUtils.toUnmodifiableList(result);
        response.setCapitals(resultList
                .stream()
                .map(cd -> String.format("Country: %s - Capital%s: %s"
                        ,cd.getName().getCommon()
                        ,cd.getCapital().size()>1?"s":""
                        ,cd.getCapital().toString())
                )
                .collect(Collectors.toList())
        );
        return response;
    }

    private CountryData[] fetchDataFromAPI(String countryName) {
        String builtUri = String.format("%s%s",countryApiUri,countryName);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builtUri, CountryData[].class);
    }
}
