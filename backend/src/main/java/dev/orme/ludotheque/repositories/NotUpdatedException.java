package dev.orme.ludotheque.repositories;

import java.lang.reflect.Type;

public class NotUpdatedException extends RuntimeException {
    public NotUpdatedException(Type t) {
        super(String.format("%s was not updated", t));
    }
}
