package com.example.countryserver.util;

import com.example.countryserver.pojo.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.StopWatch;
import org.jdbi.v3.core.Jdbi;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Util {
    public static void main(String[] args) {
        System.out.println("Hello World");
        loadDatabase("countries.json",null);
    }

    public static boolean loadDatabase(String filePath, Jdbi jdbi){
        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println("Inserting into database...");


            // For calculating the time needed
            StopWatch watch = new StopWatch();
            watch.start();

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

                    handle.createUpdate("INSERT INTO country" +
                            " (country_code,capital,official_name,common_name,flag,population,timezone,independent)" +
                            " VALUES (:countryCode,:capital,:officialName,:commonName,:flag,:population,:timezone,:independent)")
                            .bind("countryCode",countryCode)
                            .bind("capital",capital)
                            .bind("officialName",officialName)
                            .bind("commonName",commonName)
                            .bind("flag",flag)
                            .bind("population",population)
                            .bind("timezone",timezone)
                            .bind("independent",independent)
                            .execute();



                    // For country_geo
                    float area = country.getArea();
                    float latitude = country.getLatlng()[0];
                    float longitude = country.getLatlng()[1];
                    String region = country.getRegion();
                    String subregion = country.getSubregion();
                    String continent = country.getContinents()!=null ? country.getContinents().get(0) : "Unspecified";

                    // System.out.println(area+" "+latitude+" "+ longitude+" "+region+" " +subregion+" "+continent);

                    handle.createUpdate("INSERT INTO country_geo" +
                            " (country_code,area,latitude,longitude,region,subregion,continent)" +
                            " VALUES (:countryCode,:area,:latitude,:longitude,:region,:subregion,:continent)")
                            .bind("countryCode",countryCode)
                            .bind("area",area)
                            .bind("latitude",latitude)
                            .bind("longitude",longitude)
                            .bind("region",region)
                            .bind("subregion",subregion)
                            .bind("continent",continent)
                            .execute();


                    // For language
                    if(country.getLanguages()!=null) {
                        country.getLanguages().forEach((languageCode, name) -> {
                            // System.out.println(languageCode + " " + name);
                            handle.createUpdate("INSERT INTO language" +
                                    " (language_code,name,country_code)" +
                                    " VALUES (:languageCode,:name,:countryCode)")
                                    .bind("languageCode",languageCode)
                                    .bind("name",name)
                                    .bind("countryCode",countryCode)
                                    .execute();
                        });
                    }

                    // For currency
                    if(country.getCurrencies()!=null){
                        country.getCurrencies().forEach((currencyCode,value)->{
                            String name = value.get("name");
                            String symbol = value.get("symbol");

                            // System.out.println(currencyCode+" "+name+" "+symbol);
                            handle.createUpdate("INSERT INTO currency" +
                                    " (currency_code,name,symbol,country_code)" +
                                    " VALUES (:currencyCode,:name,:symbol,:countryCode)")
                                    .bind("currencyCode",currencyCode)
                                    .bind("name",name)
                                    .bind("symbol",symbol)
                                    .bind("countryCode",countryCode)
                                    .execute();
                        });
                    }
                }
                ;
            });

            // Finish
            watch.stop();
            long timeSpent = watch.getTime(TimeUnit.SECONDS);
            System.out.println("Success inserting data into the database ! Finished in "+timeSpent+" seconds");
        }
        catch(Exception e){
            System.err.println("Failed insertion in database...");
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
