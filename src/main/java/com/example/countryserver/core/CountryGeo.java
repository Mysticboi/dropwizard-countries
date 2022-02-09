package com.example.countryserver.core;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="country_geo")
public class CountryGeo {


    @Id
    @Column(name="country_code")
    @JsonIgnore
    private String countryCode;

    @Column
    private float area;

    @Column
    private float latitude;

    @Column
    private float longitude;

    @Column
    private String region;

    @Column
    private String subregion;

    @Column
    private String continent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="country_code")
    @JsonIgnore // Necessary or we'll get recursion in json jackson
    private Country country;


    public CountryGeo(){

    }

    public CountryGeo(String countryCode, float area, float latitude, float longitude, String region, String subregion, String continent, Country country) {
        this.countryCode = countryCode;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.subregion = subregion;
        this.continent = continent;
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
