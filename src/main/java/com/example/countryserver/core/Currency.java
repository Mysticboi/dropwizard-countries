package com.example.countryserver.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="currency")
public class Currency {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="currency_code")
    private String currencyCode;

    @Column
    private String name;

    @Column
    private String symbol;

    @ManyToOne
    @JoinColumn(name="country_code")
    @JsonIgnore
    private Country country;

    public Currency(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
