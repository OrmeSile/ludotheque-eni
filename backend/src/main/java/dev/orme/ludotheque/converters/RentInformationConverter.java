package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.RentInformationDTO;
import dev.orme.ludotheque.entities.RentInformation;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RentInformationConverter implements DtoConverter<RentInformation, RentInformationDTO> {

    private TimestampConverter timestampConverter;
    private GameRepository gameRepository;
    private GamePriceConverter gamePriceConverter;
    private UserRepository userRepository;

    public RentInformationConverter(TimestampConverter timestampConverter,
                                    GamePriceConverter gamePriceConverter,
                                    GameRepository gameRepository,
                                    UserRepository userRepository) {
        this.timestampConverter = timestampConverter;
        this.gamePriceConverter = gamePriceConverter;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RentInformationDTO toDto(RentInformation object) {
        if(object == null) return null;

        return RentInformationDTO.newBuilder()
                .setId(object.getId().toString())
                .setDaysRented(object.getDaysRented())
                .setPriceAtRentTime(gamePriceConverter.toDto(object.getPriceAtRentTime()))
                .setMaxRentDaysAtRentTime(object.getMaxRentDaysAtRentTime())
                .setGameId(object.getGame().getId().toString())
                .setUserId(object.getUser().getId().toString())
                .setTimestampOfRent(timestampConverter.toDto(object.getTimeOfRent()))
                .setTimestampOfReturn(timestampConverter.toDto(object.getTimeOfReturn()))
                .build();
    }

    @Override
    public RentInformation fromDto(RentInformationDTO rentInformationDTO) {
        if(rentInformationDTO == null) return null;

        return new RentInformation(
                UUID.fromString(rentInformationDTO.getId()),
                rentInformationDTO.getDaysRented(),
                rentInformationDTO.getMaxRentDaysAtRentTime(),
                gameRepository.getFirstById(UUID.fromString(rentInformationDTO.getGameId())),
                userRepository.getFirstById(UUID.fromString(rentInformationDTO.getUserId())),
                gamePriceConverter.fromDto(rentInformationDTO.getPriceAtRentTime()),
                timestampConverter.fromDto(rentInformationDTO.getTimestampOfRent()),
                timestampConverter.fromDto(rentInformationDTO.getTimestampOfReturn())
        );
    }
}
