package dev.orme.ludotheque.repositories;

import java.lang.reflect.Type;

public class NotDeletedException extends RuntimeException {
    public NotDeletedException(Type t) {
        super(String.format("%s was not deleted", t));
    }
}
