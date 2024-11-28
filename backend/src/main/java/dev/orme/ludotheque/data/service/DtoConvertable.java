package dev.orme.ludotheque.data.service;

public interface DtoConvertable<TObject, TDto> {
    public TDto toDto(TObject object);
    public TObject fromDto(TDto dto);
}
