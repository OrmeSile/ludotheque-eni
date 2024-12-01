package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GameDTO;
import dev.orme.ludotheque.RentInformationDTO;
import dev.orme.ludotheque.TimestampDTO;
import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class GameConverter implements DtoConverter<Game, GameDTO> {

    private GameRepository gameRepository;
    private UserRepository userRepository;
    private TimestampConverter timestampConverter;
    private GamePriceConverter gamePriceConverter;

    public GameConverter(
            GameRepository gameRepository,
            UserRepository userRepository,
            TimestampConverter timestampConverter,
            GamePriceConverter gamePriceConverter
    ) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.timestampConverter = timestampConverter;
        this.gamePriceConverter = gamePriceConverter;
    }

    @Override
    public GameDTO toDto(Game game) {

        var instantFromGame = game.getTimeOfCreation();
        var timeStampDTO = timestampConverter.toDto(instantFromGame);

        var builder = GameDTO.newBuilder();
        builder
                .setId(game.getId().toString())
                .setName(game.getName())
                .setDescription(game.getDescription())
                .setIsRented(game.isRented())
                .setIsActive(game.isActive())
                .setTimeOfCreation(timeStampDTO)
                .setRenterId(game.getRenter().getId().toString())
                .setCurrentPrice(gamePriceConverter.toDto(game.getGamePrice()))
                .setCurrentRentInformation(RentInformationDTO.newBuilder());
        return builder.build();
    }

    @Override
    public Game fromDto(GameDTO gameDTO) {

        var zonedDateTime = ZonedDateTime
                .ofInstant(Instant
                        .ofEpochSecond(
                                gameDTO.getTimeOfCreation().getSeconds(),
                                gameDTO.getTimeOfCreation().getNanos()
                        ),
                        ZoneId.systemDefault()

                );
        return new Game(
                UUID.fromString(gameDTO.getId()),
                gameDTO.getName(),
                gameDTO.getDescription(),
                gameDTO.getIsRented(),
                gameDTO.getIsActive(),
                zonedDateTime,
                userRepository.getFirstById(UUID.fromString(gameDTO.getRenterId())
                ));
    }
}
