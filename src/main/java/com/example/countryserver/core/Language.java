package com.example.countryserver.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="language_code")
    private String languageCode;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="country_code")
    @JsonIgnore
    private Country country;

    public Language(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
