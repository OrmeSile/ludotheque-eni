package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.TimestampDTO;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class BaseController {

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE})
    public String index() {
        var timeStamp = Instant.now();
        return TimestampDTO.newBuilder()
                .setNanos(timeStamp.getNano())
                .setSeconds(timeStamp.getEpochSecond())
                .build()
                .toString();
    }

    @GetMapping(value = "/testuser")
    public String test() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
