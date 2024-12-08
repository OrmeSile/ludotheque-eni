package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GamePriceDTO;
import dev.orme.ludotheque.entities.GamePrice;
import dev.orme.ludotheque.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GamePriceConverter implements DtoConvertable<GamePrice, GamePriceDTO> {

    private TimestampConverter timestampConverter;
    private GameRepository gameRepository;

    public GamePriceConverter(TimestampConverter timestampConverter, GameRepository gameRepository) {
        this.timestampConverter = timestampConverter;
        this.gameRepository = gameRepository;
    }

    @Override
    public GamePriceDTO toDto(GamePrice object) {

        if(object == null) return null;

        return GamePriceDTO.newBuilder()
                .setGameId(object.getGame().getId().toString())
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
        gamePrice.setGame(gameRepository.getFirstById(UUID.fromString(gamePriceDTO.getGameId())));
        return gamePrice;
    }
}
