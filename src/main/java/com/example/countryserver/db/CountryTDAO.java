package com.example.countryserver.db;

import com.example.countryserver.core.CountryT;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryTDAO extends AbstractDAO<CountryT> {
    public CountryTDAO(SessionFactory factory){
        super(factory);
    }

    public List<CountryT> findAll(){
        return this.currentSession().createQuery("SELECT c FROM CountryT c",CountryT.class).getResultList();
    }
}
