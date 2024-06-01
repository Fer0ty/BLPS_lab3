package ru.artemiyandarina.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ru.artemiyandarina.lab3.config.properties")
public class BlpsLab3Application {

    public static void main(String[] args) {
        SpringApplication.run(BlpsLab3Application.class, args);
    }

}
