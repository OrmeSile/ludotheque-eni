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
    private RentInformationConverter rentInformationConverter;

    public GameConverter(
            GameRepository gameRepository,
            UserRepository userRepository,
            TimestampConverter timestampConverter,
            GamePriceConverter gamePriceConverter,
            RentInformationConverter rentInformationConverter
    ) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.timestampConverter = timestampConverter;
        this.gamePriceConverter = gamePriceConverter;
        this.rentInformationConverter = rentInformationConverter;
    }

    @Override
    public GameDTO toDto(Game game) {
        if(game == null) return null;

        var builder = GameDTO.newBuilder();
        if(game.getId() != null)
            builder.setId(game.getId().toString());
        if(game.getName() != null)
            builder.setName(game.getName());
        if(game.getDescription() != null)
            builder.setDescription(game.getDescription());
        builder.setIsRented(game.isRented());
        builder.setIsActive(game.isActive());
        if(game.getTimeOfCreation() != null)
            builder.setTimeOfCreation(timestampConverter.toDto(game.getTimeOfCreation()));
        if(game.getRenter() != null)
            builder.setRenterId(game.getRenter().getId().toString());
        if(game.getGamePrice() != null)
            builder.setCurrentPrice(gamePriceConverter.toDto(game.getGamePrice()));
        if(game.getCurrentRentInformation() != null)
            builder.setCurrentRentInformation(rentInformationConverter.toDto(game.getCurrentRentInformation()));
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