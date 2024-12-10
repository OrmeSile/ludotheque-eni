package dev.orme.ludotheque.repositories;

import java.util.UUID;

public class NotCreatedException extends Exception {
    public NotCreatedException(String type, UUID id) {
        super(String.format("%s was not created : %s already exists", type, id));
    }
}
