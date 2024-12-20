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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Transactional
    @GetMapping(value = "")
    public ResponseEntity<GameListResponse> getAllGames(@RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "40") int pageSize) throws NotFoundException {
        if (page < 0) page = 0;
        if (pageSize <= 0) pageSize = 40;
        var result = gameService.getGamePageWithSize(page, pageSize);
        var gameListResponse = GameListResponse.newBuilder()
                .addAllGames(result.getContent()
                        .stream()
                        .map(gameConverter::toDto)
                        .toList())
                .setPage(page)
                .setNext(PaginationHelper.getNextPageUrl(
                        result.getTotalPages(), page, pageSize))
                .setPrevious(PaginationHelper.getPreviousPageUrl(
                        result.getTotalPages(), page, pageSize))
                .setCount(result.getTotalElements())
                .setTotalPages(result.getTotalPages())
                .build();
        return new ResponseEntity<>(gameListResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<GetGameResponse> getGameById(@PathVariable String id) throws NotFoundException {
        var result = gameService.getGame(UUID.fromString(id));
        var dto = GetGameResponse.newBuilder().setGame(gameConverter.toDto(result)).build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CREATE_GAMES')")
    @PostMapping("/")
    public ResponseEntity<CreateGameResponse> addGame(@RequestBody CreateGameRequest createGameRequest) throws NotCreatedException, BadRequestException {
        if (!createGameRequest.hasGame())
            throw new BadRequestException();
        var createdGame = gameService.createGame(gameConverter.fromDto(createGameRequest.getGame()));
        return new ResponseEntity<>(CreateGameResponse.newBuilder().setGame(gameConverter.toDto(createdGame)).build(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_EDIT_GAMES')")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateGameResponse> updateGame(@PathVariable String id,
                                                         @RequestBody UpdateGameRequest updateGameRequest) throws NotUpdatedException, NotFoundException {
        var result = gameService.updateGame(UUID.fromString(id), gameConverter.fromDto(updateGameRequest.getGame()));
        var dto = UpdateGameResponse.newBuilder().setGame(gameConverter.toDto(result)).build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DELETE_GAMES')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable String id) throws NotFoundException {
        gameService.deleteGame(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
