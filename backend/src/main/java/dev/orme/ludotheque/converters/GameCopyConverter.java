package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.GameCopyDTO;
import dev.orme.ludotheque.entities.GameCopy;
import org.springframework.stereotype.Service;

import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameCopyConverter implements DtoConvertable<GameCopy, GameCopyDTO> {

    private final RentInformationConverter rentInformationConverter;
    private final GameConverter gameConverter;
    private final GamePriceConverter gamePriceConverter;
    private final WearStatusConverter wearStatusConverter;

    public GameCopyConverter(GameConverter gameConverter, GamePriceConverter gamePriceConverter, RentInformationConverter rentInformationConverter, WearStatusConverter wearStatusConverter) {
        this.gameConverter = gameConverter;
        this.gamePriceConverter = gamePriceConverter;
        this.rentInformationConverter = rentInformationConverter;
        this.wearStatusConverter = wearStatusConverter;

    }

    @Override
    public GameCopyDTO toDto(GameCopy object) {
        return GameCopyDTO.newBuilder()
                          .setId(object.getId()
                                       .toString())
                          .setGame(gameConverter.toDto(object.getGame()))
                          .setCurrentPrice(gamePriceConverter.toDto(object.getCurrentGamePrice()))
                          .setCurrentRentInformation(rentInformationConverter.toDto(object.getCurrentRentInformation()))
                          .setIsRented(object.isRented())
                          .setIsActive(object.isActive())
                          .addAllPrices(object.getGamePrices()
                                              .stream()
                                              .map(gamePriceConverter::toDto)
                                              .toList())
                          .addAllRentInformation(object.getRentInformations()
                                                       .stream()
                                                       .map(rentInformationConverter::toDto)
                                                       .toList())
                          .build();
    }

    @Override
    public GameCopy fromDto(GameCopyDTO gameCopyDTO) {
        return new GameCopy(
                UUID.fromString(gameCopyDTO.getId()),
                gameConverter.fromDto(gameCopyDTO.getGame()),
                rentInformationConverter.fromDto(gameCopyDTO.getCurrentRentInformation()),
                gamePriceConverter.fromDto(gameCopyDTO.getCurrentPrice()),
                gameCopyDTO.getRentInformationList().stream().map(rentInformationConverter::fromDto).collect(Collectors.toCollection(TreeSet::new)),
                gameCopyDTO.getPricesList().stream().map(gamePriceConverter::fromDto).collect(Collectors.toCollection(TreeSet::new)),
                wearStatusConverter.fromDto(gameCopyDTO.getWearStatus()),
                gameCopyDTO.getIsRented(),
                gameCopyDTO.getIsActive()
                );
    }
}
