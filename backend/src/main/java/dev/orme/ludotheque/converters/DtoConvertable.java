package dev.orme.ludotheque.converters;

public interface DtoConvertable<TObject, TDto> {
    public TDto toDto(TObject object);
    public TObject fromDto(TDto dto);
}
