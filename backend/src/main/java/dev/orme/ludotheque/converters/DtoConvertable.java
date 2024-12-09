package dev.orme.ludotheque.converters;

import org.checkerframework.dataflow.qual.Pure;

public interface DtoConvertable<TObject, TDto> {
    @Pure
    public TDto toDto(TObject object);
    @Pure
    public TObject fromDto(TDto dto);
}
