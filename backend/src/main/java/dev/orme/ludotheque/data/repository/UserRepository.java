package dev.orme.ludotheque.data.repository;

import dev.orme.ludotheque.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    public User findByUsername(String username);

    public User getFirstById(UUID id);
}
