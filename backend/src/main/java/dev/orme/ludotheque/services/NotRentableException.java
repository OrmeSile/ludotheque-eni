package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.GameCopy;

public class NotRentableException extends RuntimeException {
    public NotRentableException(GameCopy gameCopy) {
        super(String.format("game %s is not rentable", gameCopy.getGame().getName()));
    }
}
