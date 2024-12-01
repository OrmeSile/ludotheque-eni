package dev.orme.ludotheque.services;

public record ServiceResponse<TResponseContent>(ResponseStatus status, TResponseContent content, String error){}
