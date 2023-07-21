package com.example.countryserver;

import com.example.countryserver.core.*;
import com.example.countryserver.db.CountryDAO;
import com.example.countryserver.db.CountryTDAO;
import com.example.countryserver.health.BasicHealthCheck;
import com.example.countryserver.resources.CountryResource;
import com.example.countryserver.resources.CountryTResource;
import com.example.countryserver.util.Util;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;

public class ServerApplication extends Application<ServerConfiguration> {
    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }

    @Override
    public String getName(){
        return "CountryServer";
    }

    // Adding Hibernate
    private final HibernateBundle<ServerConfiguration> hibernate = new HibernateBundle<ServerConfiguration>(CountryT.class, Country.class, CountryGeo.class, Language.class, Currency.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap){
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment){

        // JDBI for inserting data ( Can use Hibernate later )
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment,configuration.getDataSourceFactory(),"mysql");

        if(configuration.getShouldInsertData()){
            Util.populateDatabase(configuration.getJsonFilePath(),jdbi);
        }

        //Adding a basic HealthCheck
        final BasicHealthCheck basicHealthCheck = new BasicHealthCheck(configuration.getAuthor());
        environment.healthChecks().register("author",basicHealthCheck);

        // Country TEST
        final CountryTDAO countryTDAO = new CountryTDAO(hibernate.getSessionFactory());
        final CountryTResource countryTResource = new CountryTResource(countryTDAO);
        environment.jersey().register(countryTResource);

        // Country
        final CountryDAO countryDAO = new CountryDAO(hibernate.getSessionFactory());
        final CountryResource countryResource = new CountryResource(countryDAO);
        environment.jersey().register(countryResource);

    }
}
