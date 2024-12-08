package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GenreDTO;
import dev.orme.ludotheque.entities.Genre;
import org.springframework.stereotype.Service;

@Service
public class GenreConverter  implements DtoConvertable<Genre, GenreDTO> {

    @Override
    public GenreDTO toDto(Genre object) {
        return GenreDTO.newBuilder()
                .setId(object.getId().toString())
                .setName(object.getName())
                .build();
    }

    @Override
    public Genre fromDto(GenreDTO genreDTO) {
        return new Genre(genreDTO.getName());
    }
}
