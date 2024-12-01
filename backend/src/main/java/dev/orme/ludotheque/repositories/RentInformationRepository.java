package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.RentInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RentInformationRepository extends CrudRepository<RentInformation, UUID> {
}
