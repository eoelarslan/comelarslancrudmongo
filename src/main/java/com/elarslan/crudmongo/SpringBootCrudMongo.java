package com.elarslan.crudmongo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@EnableMongoRepositories
@SpringBootApplication
public class SpringBootCrudMongo
{
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBootCrudMongo.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
