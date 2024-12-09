package dev.orme.ludotheque.repositories;

import java.lang.reflect.Type;

public class NotCreatedException extends RuntimeException {
    public NotCreatedException(Type t) {
        super(String.format("%s was not created", t));
    }
}
