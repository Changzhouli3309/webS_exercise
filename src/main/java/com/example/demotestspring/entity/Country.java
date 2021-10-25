package com.example.demotestspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "country")
@Getter
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Setter
    private String name;

    @Setter
    private int population;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ElementCollection
    private List<City> cities= new ArrayList<>();

    public Country(String name, int population) {
        this.name = name;
        this.population = population;
    }
}
