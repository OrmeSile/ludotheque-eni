package dev.orme.ludotheque.data.repository;

import dev.orme.ludotheque.data.entity.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
}
