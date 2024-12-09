package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.GameCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameCopyRepository extends CrudRepository<GameCopy, UUID> {
    GameCopy getFirstById(UUID id);
}
