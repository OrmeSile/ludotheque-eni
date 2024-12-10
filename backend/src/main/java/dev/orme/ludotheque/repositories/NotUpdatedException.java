package dev.orme.ludotheque.repositories;

import java.util.UUID;

public class NotUpdatedException extends Exception {
    public NotUpdatedException(String type, UUID id) {
        super(String.format("%s with id %s was not updated", type, id));
    }
}
