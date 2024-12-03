package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID>, PagingAndSortingRepository<Game, UUID> {
    Game getFirstById(UUID id);
}
