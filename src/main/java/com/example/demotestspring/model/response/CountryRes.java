package com.example.demotestspring.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CountryRes {
    private int id;
    private String name;
    private int population;
    private List<String> cities;
}
