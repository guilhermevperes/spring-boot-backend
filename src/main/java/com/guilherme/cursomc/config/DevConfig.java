package com.guilherme.cursomc.config;

import com.guilherme.cursomc.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDataBase() throws Exception {
        System.out.println("strategy => " + strategy);
        if (!"create".equals(strategy)) {
            return false;
        }
        dbService.instantiateTestDataBase();
        return true;
    }

}
