package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GenreDTO;
import dev.orme.ludotheque.entities.Genre;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenreConverter  implements DtoConvertable<Genre, GenreDTO> {

    @Override
    public GenreDTO toDto(Genre object) {
        if (object == null) return null;

        return GenreDTO.newBuilder()
                .setId(object.getId().toString())
                .setName(object.getName())
                .build();
    }

    @Override
    public Genre fromDto(GenreDTO genreDTO) {
        if (genreDTO == null) return null;

        var genre = new Genre(genreDTO.getName());
        if(genreDTO.getId().isBlank()) return genre;
        genre.setId(UUID.fromString(genreDTO.getId()));
        return genre;
    }
}
