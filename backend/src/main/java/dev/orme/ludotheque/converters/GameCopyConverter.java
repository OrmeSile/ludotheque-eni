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
        if (object == null) return null;

        var gameCopy = GameCopyDTO.newBuilder()
                .setId(object.getId()
                        .toString())
                .setGame(gameConverter.toDto(object.getGame()))
                .setIsRented(object.isRented())
                .setIsActive(object.isActive())
                .addAllPrices(object.getGamePrices()
                        .stream()
                        .map(gamePriceConverter::toDto)
                        .toList())
                .addAllRentInformation(object.getRentInformations()
                        .stream()
                        .map(rentInformationConverter::toDto)
                        .toList());
        if(object.getCurrentGamePrice() != null)
            gameCopy.setCurrentPrice(gamePriceConverter.toDto(object.getCurrentGamePrice()));
        if(object.getCurrentRentInformation() != null)
            gameCopy.setCurrentRentInformation(rentInformationConverter.toDto(object.getCurrentRentInformation()));

        return gameCopy.build();
    }

    @Override
    public GameCopy fromDto(GameCopyDTO gameCopyDTO) {
        if (gameCopyDTO == null) return null;

        var gameCopy = new GameCopy();
        if(!gameCopyDTO.getId().isBlank())
            gameCopy.setId(UUID.fromString(gameCopyDTO.getId()));
        gameCopy.setCurrentRentInformation(rentInformationConverter.fromDto(gameCopyDTO.getCurrentRentInformation()));
        gameCopy.setGame(gameConverter.fromDto(gameCopyDTO.getGame()));
        gameCopy.setCurrentGamePrice(gamePriceConverter.fromDto(gameCopyDTO.getCurrentPrice()));
        gameCopy.setRentInformations(gameCopyDTO.getRentInformationList().stream().map(rentInformationConverter::fromDto).collect(Collectors.toCollection(TreeSet::new)));
        gameCopy.setGamePrices(gameCopyDTO.getPricesList().stream().map(gamePriceConverter::fromDto).collect(Collectors.toCollection(TreeSet::new)));
        gameCopy.setWearStatus(wearStatusConverter.fromDto(gameCopyDTO.getWearStatus()));
        gameCopy.setRented(gameCopyDTO.getIsRented());
        gameCopy.setActive(gameCopyDTO.getIsActive());
        return gameCopy;
    }
}
