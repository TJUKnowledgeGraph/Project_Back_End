package com.example.knowledgegraph.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfiguration {
    //@Value("${spring.neo4j.url}")
    private String url = "bolt://192.168.1.128:7687";
    //@Value("${spring.neo4j.authentication.username}")
    private String username = "";
    //@Value("${spring.neo4j.authentication.password}")
    private String password = "";

    @Bean(name = "driver")
    public Driver initDriver() {
        Driver driver;
        try {
            //neo4j地址 账号 密码
            driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
