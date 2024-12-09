package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GameDTO;
import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class GameConverter implements DtoConvertable<Game, GameDTO> {

    private final GenreConverter genreConverter;
    private final TimestampConverter timestampConverter;

    public GameConverter(
            TimestampConverter timestampConverter,
            GenreConverter genreConverter) {
        this.timestampConverter = timestampConverter;
        this.genreConverter = genreConverter;
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
        if(game.getTimeOfCreation() != null)
            builder.setTimeOfCreation(timestampConverter.toDto(game.getTimeOfCreation()));
        if(game.getGenres() != null)
            builder.addAllGenres(game.getGenres().stream().map(genreConverter::toDto).toList());
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
                zonedDateTime);
    }
}
