package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.WearStatusDTO;
import dev.orme.ludotheque.entities.WearStatus;
import org.checkerframework.dataflow.qual.Pure;
import org.springframework.stereotype.Service;

@Service
public class WearStatusConverter implements DtoConvertable<WearStatus, WearStatusDTO> {

    @Override
    public WearStatusDTO toDto(WearStatus object) {
        return switch (object) {
            case MINT -> WearStatusDTO.MINT;
            case EXCELLENT -> WearStatusDTO.EXCELLENT;
            case USED -> WearStatusDTO.USED;
            case DAMAGED -> WearStatusDTO.DAMAGED;
        };
    }

    @Override
    public WearStatus fromDto(WearStatusDTO wearStatusDTO) {
        return switch (wearStatusDTO) {
            case MINT -> WearStatus.MINT;
            case EXCELLENT -> WearStatus.EXCELLENT;
            case USED -> WearStatus.USED;
            case DAMAGED -> WearStatus.DAMAGED;
            default -> throw new IllegalArgumentException("Invalid wearStatusDTO");
        };
    }
}
