package dev.orme.ludotheque.repositories;

import dev.orme.ludotheque.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    public User findByUsername(String username);

    public User getFirstById(UUID id);
}
