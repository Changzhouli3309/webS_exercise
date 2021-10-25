package com.example.demotestspring.service;

import com.example.demotestspring.entity.City;
import com.example.demotestspring.entity.Country;

import java.util.List;

public interface CountryService {
    Country createCountry(String name, int population);

    List<Country> findAllCountry();

    List<Country> findCountryByName(String name);

    Country findCountryById(int id);

    void deleteCountry(int id);

    City createCity(int id, String name, int population);

    List<City> findALLCity();

    List<City> findCityByName(String name);

    List<City> findALLCityByCounty(int id);

    City findCityById(int id);

    void deleteCity(int id);
}
