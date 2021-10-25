package com.example.demotestspring.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityRes {
    private int id;
    private String name;
    private int population;
    private String country;
}
