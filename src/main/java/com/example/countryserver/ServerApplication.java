package com.example.countryserver;

import com.example.countryserver.core.CountryT;
import com.example.countryserver.db.CountryDAO;
import com.example.countryserver.health.BasicHealthCheck;
import com.example.countryserver.resources.CountryResource;
import com.example.countryserver.util.Util;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
    private final HibernateBundle<ServerConfiguration> hibernate = new HibernateBundle<ServerConfiguration>(CountryT.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap){
        System.out.println("<<<<<<<<<<<Hello from initialization");
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment){
        System.out.println("<<<<<<<<<<<<<<<<<<<Hello from countryServer");

        // JDBI for inserting data ( Can use Hibernate later )
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment,configuration.getDataSourceFactory(),"mysql");

        if(configuration.getShouldInsertData()){
            Util.loadDatabase(configuration.getJsonFilePath(),jdbi);
        }

        //Adding a basic HealthCheck
        final BasicHealthCheck basicHealthCheck = new BasicHealthCheck(configuration.getAuthor());
        environment.healthChecks().register("author",basicHealthCheck);

        // Country
        final CountryDAO countryDAO = new CountryDAO(hibernate.getSessionFactory());
        final CountryResource countryResource = new CountryResource(countryDAO);
        environment.jersey().register(countryResource);

    }
}