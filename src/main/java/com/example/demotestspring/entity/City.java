package com.example.demotestspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "city")
@Getter
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Setter
    private String name;

    @Setter
    private int population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @Setter
    Country country;

    public City(String name, int population, Country country) {
        this.name = name;
        this.population = population;
        this.country = country;
    }
}
