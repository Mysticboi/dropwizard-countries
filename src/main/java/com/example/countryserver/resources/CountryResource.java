package com.example.countryserver.resources;

import com.example.countryserver.core.Country;
import com.example.countryserver.db.CountryDAO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {
    private final CountryDAO countryDAO;

    public CountryResource(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @GET
    @UnitOfWork
    public List<Country> getAllCountries(){
        return countryDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("/commonName")
    public List<String> getAllCommonNames(){
        return countryDAO.getAllCommonNames();
    }

    @GET
    @UnitOfWork
    @Path("/countryCode")
    public List<String> getAllCountryCodes(){
        return countryDAO.getAllCountryCodes();
    }

    @GET
    @UnitOfWork
    @Path("/{countryCode}")
    public Country getCountryByCode(@PathParam("countryCode") String countryCode){
        System.out.println("<<< Country demanded with code: "+countryCode);
        return countryDAO.findByCode(countryCode);
    }
}
