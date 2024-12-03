package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.GameDTO;
import dev.orme.ludotheque.GameListPaginationDTO;
import dev.orme.ludotheque.converters.GameConverter;
import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.helpers.PaginationHelper;
import dev.orme.ludotheque.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/game", consumes = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE})
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

    @GetMapping(value = "/")
    public ResponseEntity<GameListPaginationDTO> getAllGames(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "50") int size) {
        page--;
        if (page < 0) page = 0;
        if (size <= 0) size = 50;
        var result = gameService.getGamePageWithSize(page, size);
        var currentUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                    .build()
                                                    .toUriString();
        var gameListDTO = GameListPaginationDTO.newBuilder()
                                               .addAllGames(result.getContent()
                                                                  .stream()
                                                                  .map(gameConverter::toDto)
                                                                  .toList())
                                               .setPage(page + 1)
                                               .setNext(PaginationHelper.getNextPageUrl(currentUri, result.getTotalPages(), page, size))
                                               .setPrevious(PaginationHelper.getPreviousPageUrl(currentUri, result.getTotalPages(), page, size))
                                               .setCount(result.getTotalElements())
                                               .setTotalPages(result.getTotalPages())
                                               .build();
        return new ResponseEntity<>(gameListDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/test", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public ResponseEntity<GameDTO> addGameTest() {
        var game = gameService.createGame(new Game());
        if (game == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to add game");

        return new ResponseEntity<>(gameConverter.toDto(game), HttpStatus.CREATED);
    }
}
