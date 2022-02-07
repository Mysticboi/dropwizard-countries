package com.example.countryserver.util;

import com.example.countryserver.pojo.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static void main(String[] args) {
        System.out.println("Hello World");
        loadDatabase("countries.json",null);
    }

    public static boolean loadDatabase(String filePath, Jdbi jdbi){
        ObjectMapper mapper = new ObjectMapper();

        try {
            jdbi.useHandle(handle-> {
                List<Country> countries = Arrays.asList(mapper.readValue(new File(filePath), Country[].class));
                for (Country country : countries) {
//                String prettyCountry = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(country);
//                System.out.println(prettyCountry);

                    // TODO: instead of each sout insert into table

                    // For country table
                    String countryCode = country.getCca3();
                    String commonName = country.getName().get("common");
                    String officialName = country.getName().get("official");
                    boolean independent = country.isIndependent();
                    String capital = country.getCapital()!=null ? country.getCapital().get(0) : "Unspecified";
                    int population = country.getPopulation();
                    String flag = country.getFlags().get("png");
                    String timezone = country.getTimezones().get(0);

                    // System.out.println(countryCode+" "+commonName+" " + " "+capital);

                    // For country_geo
                    float area = country.getArea();
                    float latitude = country.getLatlng()[0];
                    float longitude = country.getLatlng()[1];
                    String region = country.getRegion();
                    String subregion = country.getSubregion();
                    String continent = country.getContinents()!=null ? country.getContinents().get(0) : "Unspecified";

                    // System.out.println(area+" "+latitude+" "+ longitude+" "+region+" " +subregion+" "+continent);

                    // For language
                    if(country.getLanguages()!=null) {
                        country.getLanguages().forEach((languageCode, name) -> {
                            // System.out.println(languageCode + " " + name);
                        });
                    }

                    // For currency
                    if(country.getCurrencies()!=null){
                        country.getCurrencies().forEach((currencyCode,value)->{
                            String name = value.get("name");
                            String symbol = value.get("symbol");

                            // System.out.println(currencyCode+" "+name+" "+symbol);
                        });
                    }
                }
                ;
            });
        }
        catch(Exception e){
            System.err.println(e);
        }
        return true;
    }

    private static void testJdbi(Jdbi jdbi){
        jdbi.useHandle(handle->{
            handle.createUpdate("INSERT INTO test  (name,population,capital) VALUES (:name,:population,:capital)")
                    .bind("name","Belgium")
                    .bind("population",1000)
                    .bind("capital","Brussels")
                    .execute();
        });
    }
}
