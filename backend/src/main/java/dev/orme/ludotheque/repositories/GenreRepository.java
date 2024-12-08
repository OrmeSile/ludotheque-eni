package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GenreRepository extends CrudRepository<Genre, UUID> {
}
