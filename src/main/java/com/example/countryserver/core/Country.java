package com.example.countryserver.core;

import javax.persistence.*;

@Entity
@Table(name="country")
public class Country {
    @Id
    @Column(name="country_code")
    private String countryCode;

    @Column
    private String capital;

    @Column(name="official_name")
    private String officialName;

    @Column(name="common_name")
    private String commonName;

    @Column
    private String flag;

    @Column
    private int population;

    @Column
    private String timezone;

    @Column
    private boolean independent;

    // Shared primary key JPA https://www.baeldung.com/jpa-one-to-one
    @OneToOne(mappedBy="country",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CountryGeo countryGeo;

    public Country(){

    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public CountryGeo getCountryGeo() {
        return countryGeo;
    }

    public void setCountryGeo(CountryGeo countryGeo) {
        this.countryGeo = countryGeo;
    }
}
