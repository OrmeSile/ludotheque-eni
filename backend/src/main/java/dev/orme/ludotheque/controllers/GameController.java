package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.GameDTO;
import dev.orme.ludotheque.converters.GameConverter;
import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final GameConverter gameConverter;

    public GameController(GameService gameService, GameConverter gameConverter) {
        this.gameService = gameService;
        this.gameConverter = gameConverter;
    }

    @PostMapping("/")
    public ResponseEntity<Game> addGame(@RequestBody GameDTO game) {
        return new ResponseEntity<>(new Game(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/test", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public ResponseEntity<GameDTO> addGameTest() {
        var game = gameService.createGame(new Game());
        if(game == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to add game");

        return new ResponseEntity<>(gameConverter.toDto(game), HttpStatus.CREATED);
    }
}
