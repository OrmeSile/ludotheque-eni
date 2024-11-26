package dev.orme.ludotheque.data.repository;

import dev.orme.ludotheque.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);

    public User getFirstById(String id);
}
