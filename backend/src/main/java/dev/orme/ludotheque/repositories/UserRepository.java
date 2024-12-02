package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsername(String username);
    User findByIdAndUsername(UUID id, String username);
    User getFirstById(UUID id);
}
