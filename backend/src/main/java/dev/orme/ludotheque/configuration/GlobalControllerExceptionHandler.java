package dev.orme.ludotheque.configuration;

import dev.orme.ludotheque.ErrorResponse;
import dev.orme.ludotheque.repositories.NotCreatedException;
import dev.orme.ludotheque.repositories.NotDeletedException;
import dev.orme.ludotheque.repositories.NotFoundException;
import dev.orme.ludotheque.repositories.NotUpdatedException;
import dev.orme.ludotheque.services.NotRentableException;
import dev.orme.ludotheque.services.UserNotActiveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ErrorResponse
    handleNotFoundException(final NotFoundException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.NOT_FOUND.value()).setError(e.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotDeletedException.class)
    @ResponseBody
    ErrorResponse
    handleNotDeletedException(final NotDeletedException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.NOT_FOUND.value()).setError(e.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotUpdatedException.class)
    @ResponseBody
    ErrorResponse
    handleNotUpdatedException(final NotUpdatedException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.NOT_FOUND.value()).setError(e.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotCreatedException.class)
    @ResponseBody
    ErrorResponse
    handleNotCreatedException(final NotCreatedException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.NOT_FOUND.value()).setError(e.getLocalizedMessage()).build();

    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotRentableException.class)
    @ResponseBody
    ErrorResponse
    handleNotRentableException(final NotRentableException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.FORBIDDEN.value()).setError(e.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotActiveException.class)
    @ResponseBody
    ErrorResponse
    handleUserNotActiveException(final UserNotActiveException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.BAD_REQUEST.value()).setError(e.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ErrorResponse
    handleIllegalArgumentException(final IllegalArgumentException e) {
        return ErrorResponse.newBuilder().setCode(HttpStatus.BAD_REQUEST.value()).setError(e.getLocalizedMessage()).build();
    }
}
