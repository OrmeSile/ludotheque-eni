package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.User;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void insertUserIfNotPresent(String id, String username, String email, String givenName, String familyName) {
        var uuid = UUID.fromString(id);
        var databaseUser = userRepository.findByIdAndUsername(uuid, username);
        if(databaseUser != null) return;

        var user = new User(uuid, username, email, givenName, familyName);
        userRepository.save(user);
    }
}
