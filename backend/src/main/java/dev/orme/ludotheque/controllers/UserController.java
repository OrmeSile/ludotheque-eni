package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.GameDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "test", produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public GameDTO testGet() {
        return GameDTO.newBuilder().setDescription("Ceci est un jeu de test.").build();
    }

}
