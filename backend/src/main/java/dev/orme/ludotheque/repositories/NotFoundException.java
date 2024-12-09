package dev.orme.ludotheque.repositories;


import jakarta.persistence.Entity;

import java.lang.reflect.Type;

public class NotFoundException extends Exception {
    public NotFoundException(Type c) {
        super("Entity " + c.getTypeName() + " not found");
    }
}
