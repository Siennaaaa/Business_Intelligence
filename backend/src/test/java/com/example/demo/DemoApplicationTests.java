package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class DemoApplicationTests extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationTests.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
    }
}
