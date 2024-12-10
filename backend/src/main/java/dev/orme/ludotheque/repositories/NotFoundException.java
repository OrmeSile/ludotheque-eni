package dev.orme.ludotheque.repositories;

import java.util.UUID;

public class NotFoundException extends Exception {

    public NotFoundException(String type) {
        super(String.format("%s not found", type));
    }

    public NotFoundException(String type, UUID id) {
        super(String.format("%s with id %s not found", type, id));
    }
}
