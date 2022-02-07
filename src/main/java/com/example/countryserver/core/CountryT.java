package com.example.countryserver.core;

import javax.persistence.*;

@Entity
@Table(name="test")
public class CountryT {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="population",nullable = false)
    private int population;

    @Column(name="capital",nullable = false)
    private String capital;

    public CountryT() {
    }

    public CountryT(String name, int population, String capital) {
        this.name = name;
        this.population = population;
        this.capital = capital;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
