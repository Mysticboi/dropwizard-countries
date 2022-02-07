package com.example.countryserver.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

// Ignores any unknown properties ( or else fails)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country{

    // In country
    private String cca3;

    @JsonIgnoreProperties("nativeName")
    private Map<String,String> name;

    private boolean independent;

    private List<String> capital;

    private int population;

    private Map<String,String> flags;

    private List<String> timezones;

    // In country_geo
    private float area;

    private String region;

    private String subregion;

    private float[] latlng;

    private List<String> continents;

    private Map<String,String> languages;

    // In currency

    private Map<String,Map<String,String>> currencies;

    public Country() {
    }

    public Country(String cca3, Map<String, String> name, boolean independent, List<String> capital, int population, Map<String, String> flags, float area, String region, String subregion, float[] latlng, List<String> continents, Map<String, String> languages, List<String> timezones, Map<String,Map<String,String>> currencies) {
        this.cca3 = cca3;
        this.name = name;
        this.independent = independent;
        this.capital = capital;
        this.population = population;
        this.flags = flags;
        this.area = area;
        this.region = region;
        this.subregion = subregion;
        this.latlng = latlng;
        this.continents = continents;
        this.languages = languages;
        this.timezones = timezones;
        this.currencies = currencies;
    }

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Map<String, String> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, String> flags) {
        this.flags = flags;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
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

    public float[] getLatlng() {
        return latlng;
    }

    public void setLatlng(float[] latlng) {
        this.latlng = latlng;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public Map<String, Map<String, String>> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Map<String, String>> currencies) {
        this.currencies = currencies;
    }

    public boolean equals(Country c){
        if(c ==this){
            return true;
        }

        if(!(c instanceof Country)){
            return false;
        }

        return c.cca3.equals(this.cca3);
    }

}
