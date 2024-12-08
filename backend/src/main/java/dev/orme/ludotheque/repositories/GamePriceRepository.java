package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.GamePrice;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GamePriceRepository extends CrudRepository<GamePrice, UUID> {
}
