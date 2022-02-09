package com.example.countryserver.resources;

import com.example.countryserver.core.CountryT;
import com.example.countryserver.db.CountryTDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CountryTResource {
    private final CountryTDAO countryTDAO;

    public CountryTResource(CountryTDAO countryTDAO) {
        this.countryTDAO = countryTDAO;
    }

    @Path("/")
    @GET
    @UnitOfWork
    public List<CountryT> getAllCountries(){
        return countryTDAO.findAll();
    }
}
