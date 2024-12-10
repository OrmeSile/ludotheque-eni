package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.*;
import dev.orme.ludotheque.converters.GameConverter;
import dev.orme.ludotheque.helpers.PaginationHelper;
import dev.orme.ludotheque.repositories.NotCreatedException;
import dev.orme.ludotheque.repositories.NotFoundException;
import dev.orme.ludotheque.repositories.NotUpdatedException;
import dev.orme.ludotheque.services.GameService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/game", consumes = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class GameController {

    private final GameService gameService;
    private final GameConverter gameConverter;

    public GameController(GameService gameService, GameConverter gameConverter) {
        this.gameService = gameService;
        this.gameConverter = gameConverter;
    }

    @GetMapping(value = "/")
    public ResponseEntity<GameListResponse> getAllGames(@RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "40") int pageSize) throws NotFoundException {
        if (page < 0) page = 0;
        if (pageSize <= 0) pageSize = 40;
        var result = gameService.getGamePageWithSize(page, pageSize);
        var currentUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .build()
                .toUriString();
        var gameListResponse = GameListResponse.newBuilder()
                .addAllGames(result.getContent()
                        .stream()
                        .map(gameConverter::toDto)
                        .toList())
                .setPage(page + 1)
                .setNext(PaginationHelper.getNextPageUrl(currentUri,
                        result.getTotalPages(), page, pageSize))
                .setPrevious(PaginationHelper.getPreviousPageUrl(currentUri,
                        result.getTotalPages(), page, pageSize))
                .setCount(result.getTotalElements())
                .setTotalPages(result.getTotalPages())
                .build();
        return new ResponseEntity<>(gameListResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGameResponse> getGameById(@PathVariable String id) throws NotFoundException {
        var result = gameService.getGame(UUID.fromString(id));
        var dto = GetGameResponse.newBuilder().setGame(gameConverter.toDto(result)).build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CreateGameResponse> addGame(@RequestBody CreateGameRequest createGameRequest) throws NotCreatedException, BadRequestException {
        if (!createGameRequest.hasGame() || createGameRequest.getGame().getId().isBlank())
            throw new BadRequestException();
        var createdGame = gameService.createGame(gameConverter.fromDto(createGameRequest.getGame()));
        return new ResponseEntity<>(CreateGameResponse.newBuilder().setGame(gameConverter.toDto(createdGame)).build(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateGameResponse> updateGame(@PathVariable String id, @RequestBody GameDTO game) throws NotUpdatedException, NotFoundException {
        var result = gameService.updateGame(UUID.fromString(id), gameConverter.fromDto(game));
        var dto = UpdateGameResponse.newBuilder().setGame(gameConverter.toDto(result)).build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable String id) throws NotFoundException {
        gameService.deleteGame(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
