package com.example.demotestspring.repository;

import com.example.demotestspring.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends CrudRepository<City,Integer> {

    List<City> findByNameIgnoreCaseStartingWith(String name);
}
