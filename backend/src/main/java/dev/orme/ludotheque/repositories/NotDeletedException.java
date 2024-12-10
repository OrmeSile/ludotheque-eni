package dev.orme.ludotheque.repositories;

import java.util.UUID;

public class NotDeletedException extends Exception {
    public NotDeletedException(String type, UUID id) {
        super(String.format("%s with id %s was not deleted", type, id));
    }
}
