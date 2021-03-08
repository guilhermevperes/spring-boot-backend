package com.guilherme.cursomc.config;

import com.guilherme.cursomc.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws Exception {
        dbService.instantiateTestDataBase();
        return true;
    }

}
