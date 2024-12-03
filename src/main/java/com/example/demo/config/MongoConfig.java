package com.example.demo.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(
                MongoClients.create("mongodb+srv://admins:admins@leaderboards.wcacf.mongodb.net/?retryWrites=true&w=majority&appName=LeaderBoards"),
                "LeaderBoards"));
    }
}
