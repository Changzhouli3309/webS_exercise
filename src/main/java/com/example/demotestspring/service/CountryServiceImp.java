package com.example.demotestspring.service;

import com.example.demotestspring.entity.City;
import com.example.demotestspring.entity.Country;
import com.example.demotestspring.repository.CityRepo;
import com.example.demotestspring.repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    CountryRepo countryRepo;
    CityRepo cityRepo;

    @Autowired
    public CountryServiceImp(CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public Country createCountry(String name, int population) {
        return countryRepo.save(new Country(name, population));
    }

    @Override
    public List<Country> findAllCountry() {
        return (List<Country>) countryRepo.findAll();
    }

    @Override
    public List<Country> findCountryByName(String name) {
        return countryRepo.findByNameIgnoreCaseStartingWith(name);
    }

    @Override
    public Country findCountryById(int id) {
        return countryRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteCountry(int id) {
        Country country = findCountryById(id);
        List<City> cities = country.getCities();
        for (City city : cities) {
            deleteCity(city.getId());
        }
        countryRepo.delete(country);
    }

    @Override
    public City createCity(int id, String name, int population) {
        Country country = findCountryById(id);
        City city = cityRepo.save(new City(name, population, country));
        country.getCities().add(city);
        countryRepo.save(country);
        return city;
    }

    @Override
    public List<City> findALLCity() {
        return (List<City>) cityRepo.findAll();
    }

    @Override
    public List<City> findCityByName(String name) {
        return cityRepo.findByNameIgnoreCaseStartingWith(name);
    }

    @Override
    public List<City> findALLCityByCounty(int id) {
        return findCountryById(id).getCities();
    }

    @Override
    public City findCityById(int id) {
        return cityRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteCity(int id) {
        City city = findCityById(id);
        Country country = city.getCountry();
        country.getCities().remove(city);
        countryRepo.save(country);
        cityRepo.delete(findCityById(id));
    }

}
