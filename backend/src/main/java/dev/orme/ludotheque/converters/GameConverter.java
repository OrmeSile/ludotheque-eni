package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GameDTO;
import dev.orme.ludotheque.entities.Game;
import org.springframework.stereotype.Service;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameConverter implements DtoConvertable<Game, GameDTO> {

    private final GenreConverter genreConverter;
    private final TimestampConverter timestampConverter;
    private final ImageInfoConverter imageInfoConverter;

    public GameConverter(
            TimestampConverter timestampConverter,
            GenreConverter genreConverter,
            ImageInfoConverter imageInfoConverter
    ) {
        this.timestampConverter = timestampConverter;
        this.genreConverter = genreConverter;
        this.imageInfoConverter = imageInfoConverter;
    }

    @Override
    public GameDTO toDto(Game game) {
        if (game == null) return null;

        var builder = GameDTO.newBuilder();

        builder.setName(game.getName());

        if (game.getId() != null)
            builder.setId(game.getId().toString());
        if (game.getDescription() != null)
            builder.setDescription(game.getDescription());
        if (game.getTimeOfCreation() != null)
            builder.setTimeOfCreation(timestampConverter.toDto(game.getTimeOfCreation()));
        if (game.getGenres() != null)
            builder.addAllGenres(game.getGenres().stream().map(genreConverter::toDto).toList());
        if (game.getYearPublished() != 0)
            builder.setYearPublished(game.getYearPublished());
        var gameImages = game.getImages();
        if (!gameImages.isEmpty())
            builder.addAllImages(game.getImages().stream().map(imageInfoConverter::toDto).toList());
        return builder.build();
    }

        @Override
    public Game fromDto(GameDTO gameDTO) {
        if (gameDTO == null) return null;

        var game = new Game();

        game.setName(gameDTO.getName());

        var zonedDateTime = timestampConverter.fromDto(gameDTO.getTimeOfCreation());
        if(zonedDateTime != null)
            game.setTimeOfCreation(zonedDateTime);

        if (!gameDTO.getId().isBlank())
            game.setId(UUID.fromString(gameDTO.getId()));
        if(!gameDTO.getDescription().isBlank())
            game.setDescription(gameDTO.getDescription());
        if(gameDTO.getYearPublished() != 0)
            game.setYearPublished(gameDTO.getYearPublished());
        if(!gameDTO.getImagesList().isEmpty())
            game.setImages(gameDTO.getImagesList().stream().map(imageInfoConverter::fromDto).collect(Collectors.toCollection(TreeSet::new)));

        return game;
    }
}
