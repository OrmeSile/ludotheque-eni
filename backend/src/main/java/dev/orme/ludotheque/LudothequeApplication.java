package dev.orme.ludotheque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class LudothequeApplication {

    private static final Logger log = LoggerFactory.getLogger(LudothequeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LudothequeApplication.class, args);
    }

}
