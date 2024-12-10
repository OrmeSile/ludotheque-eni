package dev.orme.ludotheque.controllers;

import dev.orme.ludotheque.RentGameRequest;
import dev.orme.ludotheque.RentGameResponse;
import dev.orme.ludotheque.converters.GameCopyConverter;
import dev.orme.ludotheque.repositories.NotFoundException;
import dev.orme.ludotheque.services.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/rent", consumes = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_PROTOBUF_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class RentController{

    RentService rentService;
    GameCopyConverter gameCopyConverter;

    public RentController(RentService rentService, GameCopyConverter gameCopyConverter) {
        this.rentService = rentService;
        this.gameCopyConverter = gameCopyConverter;
    }

    @PostMapping("/")
    public ResponseEntity<RentGameResponse> rentGame(@RequestBody RentGameRequest rentGameRequest) throws NotFoundException {
        var rentedGame = rentService.rentGame(UUID.fromString(rentGameRequest.getUserId()),
                UUID.fromString(rentGameRequest.getGameCopyId()));
        var rentGameResponse =
                RentGameResponse.newBuilder().setRentedGame(gameCopyConverter.toDto(rentedGame)).build();
        return new ResponseEntity<>(rentGameResponse, HttpStatus.OK);
    }
}
