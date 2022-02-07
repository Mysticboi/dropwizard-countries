package com.example.countryserver.health;

import com.codahale.metrics.health.HealthCheck;

public class BasicHealthCheck extends HealthCheck {
    private final String author;

    public BasicHealthCheck(String author){
        this.author = author;
    }

    @Override
    protected Result check() throws Exception{
        String expected = "Walid Oulderra";
        if(!expected.equals(author)){
            return Result.unhealthy("Author isn't matching");
        }
        return Result.healthy();
    }
}
