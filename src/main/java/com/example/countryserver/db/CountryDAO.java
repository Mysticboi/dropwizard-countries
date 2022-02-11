package com.example.countryserver.db;

import com.example.countryserver.core.Country;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryDAO extends AbstractDAO<Country> {
    public CountryDAO(SessionFactory factory){
        super(factory);
    }

    public List<Country> findAll(){
        return this.currentSession().createQuery("SELECT c FROM Country c",Country.class).getResultList();
    }

    public List<String> getAllCommonNames(){
        return this.currentSession().createQuery("SELECT c.commonName FROM Country  c",String.class).getResultList();
    }

    public List<String> getAllCountryCodes(){
        return currentSession().createQuery("SELECT c.countryCode FROM Country c",String.class).getResultList();
    }

    public Country findByCode(String code){
        return get(code);
    }
}
