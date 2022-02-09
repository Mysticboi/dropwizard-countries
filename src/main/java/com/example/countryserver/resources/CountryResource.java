package com.example.countryserver.resources;

import com.example.countryserver.core.Country;
import com.example.countryserver.db.CountryDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
