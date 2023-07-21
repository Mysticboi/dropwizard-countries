package com.example.countryserver.resources;

import com.example.countryserver.core.CountryT;
import com.example.countryserver.db.CountryTDAO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CountryTResource {
    private final CountryTDAO countryTDAO;

    public CountryTResource(CountryTDAO countryTDAO) {
        this.countryTDAO = countryTDAO;
    }

    @GET
    @UnitOfWork
    public List<CountryT> getAllCountries(){
        return countryTDAO.findAll();
    }
}
