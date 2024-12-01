package dev.orme.ludotheque.converters;

public interface DtoConverter<TObject, TDto> {
    public TDto toDto(TObject object);
    public TObject fromDto(TDto dto);
}
