package com.example.demotestspring.controller;

import com.example.demotestspring.entity.City;
import com.example.demotestspring.entity.Country;
import com.example.demotestspring.model.request.CityReq;
import com.example.demotestspring.model.response.CityRes;
import com.example.demotestspring.model.request.CountryReq;
import com.example.demotestspring.model.response.CountryRes;
import com.example.demotestspring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // http://localhost:8080/api
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity<CountryRes> createCountry(@RequestBody CountryReq countryReq) {
        Country country = countryService.createCountry(countryReq.getName(), countryReq.getPopulation());
        List<String> cities = new ArrayList<>();
        return ResponseEntity.ok(new CountryRes(country.getId(), country.getName(), country.getPopulation(), cities));
    }

    @GetMapping("/country")
    public ResponseEntity<List<CountryRes>> findAllCountry() {
        List<Country> countries = countryService.findAllCountry();

        List<CountryRes> responses = new ArrayList<>();
        for (Country country : countries) {
            List<String> cities = country.getCities().stream().map(City::getName).collect(Collectors.toList());
            responses.add(new CountryRes(country.getId(), country.getName(), country.getPopulation(), cities));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/country/name/{name}")
    public ResponseEntity<List<CountryRes>> findCountryByName(@PathVariable String name) {
        List<Country> countries = countryService.findCountryByName(name);

        if (countries.size()==0){
            return ResponseEntity.notFound().build();
        }else {
            List<CountryRes> responses = new ArrayList<>();
            for (Country country : countries) {
                List<String> cities = country.getCities().stream().map(City::getName).collect(Collectors.toList());
                responses.add(new CountryRes(country.getId(), country.getName(), country.getPopulation(), cities));
            }

            return ResponseEntity.ok(responses);
        }
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<CountryRes> findCountryById(@PathVariable int id) {
        try {
            Country country = countryService.findCountryById(id);
            List<String> cities = country.getCities().stream().map(City::getName).collect(Collectors.toList());
            return ResponseEntity.ok(new CountryRes(country.getId(), country.getName(), country.getPopulation(), cities));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.ok("Country with ID: <" + id + "> is deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/country/{id}/city")
    public ResponseEntity<CityRes> createCityInCountry(@PathVariable int id, @RequestBody CityReq cityReq) {
        City city = countryService.createCity(id, cityReq.getName(), cityReq.getPopulation());
        return ResponseEntity.ok(new CityRes(city.getId(), city.getName(), city.getPopulation(),
                city.getCountry().getName()));
    }

    @GetMapping("/city")
    public ResponseEntity<List<CityRes>> findAllCity() {
        List<City> cities = countryService.findALLCity();

        List<CityRes> responses = new ArrayList<>();
        for (City city :cities) {
            responses.add(new CityRes(city.getId(), city.getName(), city.getPopulation(),
                    city.getCountry().getName()));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/city/name/{name}")
    public ResponseEntity<List<CityRes>> findCityByName(@PathVariable String name) {
        List<City> cities = countryService.findCityByName(name);

        if (cities.size()==0){
            return ResponseEntity.notFound().build();
        }else {
            List<CityRes> responses = new ArrayList<>();
            for (City city :cities) {
                responses.add(new CityRes(city.getId(), city.getName(), city.getPopulation(),
                        city.getCountry().getName()));
            }
            return ResponseEntity.ok(responses);
        }
    }

    @GetMapping("/country/{id}/city")
    public ResponseEntity<List<CityRes>> findAllCityByCountry(@PathVariable int id) {
        List<City> cities = countryService.findALLCityByCounty(id);

        List<CityRes> responses = new ArrayList<>();
        for (City city :cities) {
            responses.add(new CityRes(city.getId(), city.getName(), city.getPopulation(),
                    city.getCountry().getName()));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityRes> findCityById(@PathVariable int id) {
        try {
            City city = countryService.findCityById(id);
            return ResponseEntity.ok(new CityRes(city.getId(), city.getName(), city.getPopulation(),
                    city.getCountry().getName()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable int id) {
        try {
            countryService.deleteCity(id);
            return ResponseEntity.ok("City with ID: <" + id + "> is deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
