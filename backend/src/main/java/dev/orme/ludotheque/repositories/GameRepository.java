package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
    Game getFirstById(UUID id);
}
