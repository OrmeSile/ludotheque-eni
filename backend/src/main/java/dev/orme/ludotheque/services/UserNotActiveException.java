package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.User;

public class UserNotActiveException extends RuntimeException {
    public UserNotActiveException(User user) {
        super(String.format("user %s is not active",user.getId()));
    }
}
