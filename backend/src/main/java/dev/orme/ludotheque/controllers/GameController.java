package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.Empty;
import dev.orme.ludotheque.RentGameDto;
import dev.orme.ludotheque.services.RentGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    private final RentGameService rentGameService;

    @Autowired
    public GameController(RentGameService rentGameService) {
        this.rentGameService = rentGameService;
    }


}
