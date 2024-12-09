package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GamePriceDTO;
import dev.orme.ludotheque.entities.GamePrice;
import dev.orme.ludotheque.repositories.GameCopyRepository;
import dev.orme.ludotheque.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GamePriceConverter implements DtoConvertable<GamePrice, GamePriceDTO> {

    private final GameCopyRepository gameCopyRepository;
    private TimestampConverter timestampConverter;

    public GamePriceConverter(
            TimestampConverter timestampConverter,
            GameCopyRepository gameCopyRepository) {
        this.timestampConverter = timestampConverter;
        this.gameCopyRepository = gameCopyRepository;
    }

    @Override
    public GamePriceDTO toDto(GamePrice object) {

        if(object == null) return null;

        return GamePriceDTO.newBuilder()
                .setGameCopyId(object.getGameCopy().getId().toString())
                .setPrice(object.getPrice())
                .setId(object.getId().toString())
                .setTimeOfPriceSet(timestampConverter.toDto(object.getTimeOfPriceSet()))
                .build();
    }

    @Override
    public GamePrice fromDto(GamePriceDTO gamePriceDTO) {
        if(gamePriceDTO == null) return null;

        var gamePrice = new GamePrice();
        gamePrice.setId(UUID.fromString(gamePriceDTO.getId()));
        gamePrice.setPrice(gamePriceDTO.getPrice());
        gamePrice.setTimeOfPriceSet(timestampConverter.fromDto(gamePriceDTO.getTimeOfPriceSet()));
        gamePrice.setGameCopy(gameCopyRepository.getFirstById(UUID.fromString(gamePriceDTO.getGameCopyId())));
        return gamePrice;
    }
}
