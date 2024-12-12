package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.TimestampDTO;
import dev.orme.ludotheque.configuration.CustomJwtAuthToken;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class BaseController {
    @GetMapping(value = "/testuser")
    public String test() {
        var jwtAuthToken = SecurityContextHolder.getContext().getAuthentication();
        if(jwtAuthToken.getClass() == CustomJwtAuthToken.class) {
            var authToken = (CustomJwtAuthToken) jwtAuthToken;
            return authToken.getEmail();
        }
        return null;
    }
}
