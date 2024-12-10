package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.RentInformationDTO;
import dev.orme.ludotheque.entities.RentInformation;
import dev.orme.ludotheque.repositories.GameCopyRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RentInformationConverter implements DtoConvertable<RentInformation, RentInformationDTO> {

    private final TimestampConverter timestampConverter;
    private final GameCopyRepository gameCopyRepository;
    private final GamePriceConverter gamePriceConverter;
    private final UserRepository userRepository;

    public RentInformationConverter(TimestampConverter timestampConverter, GamePriceConverter gamePriceConverter, GameCopyRepository gameCopyRepository, UserRepository userRepository) {
        this.timestampConverter = timestampConverter;
        this.gamePriceConverter = gamePriceConverter;
        this.gameCopyRepository = gameCopyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RentInformationDTO toDto(RentInformation object) {
        if (object == null) return null;

        var rentInformationBuilder = RentInformationDTO.newBuilder()
                                 .setId(object.getId()
                                              .toString())
                                 .setDaysRented(object.getDaysRented())
                                 .setPriceAtRentTime(gamePriceConverter.toDto(object.getPriceAtRentTime()))
                                 .setMaxRentDaysAtRentTime(object.getMaxRentDaysAtRentTime())
                                 .setGameCopyId(object.getGameCopy()
                                                      .getId()
                                                      .toString())
                                 .setUserId(object.getUser()
                                                  .getId()
                                                  .toString())
                                 .setTimestampOfRent(timestampConverter.toDto(object.getTimeOfRent()));
        if(object.getTimeOfReturn() != null)
             rentInformationBuilder.setTimestampOfReturn(timestampConverter.toDto(object.getTimeOfReturn()));
        return rentInformationBuilder.build();
    }

    @Override
    public RentInformation fromDto(RentInformationDTO rentInformationDTO) {
        if (rentInformationDTO == null) return null;

        var rentInformation = new RentInformation();
        if(!rentInformationDTO.getId().isBlank())
            rentInformation.setId(UUID.fromString(rentInformationDTO.getId()));
        rentInformation.setDaysRented(rentInformationDTO.getDaysRented());
        rentInformation.setMaxRentDaysAtRentTime(rentInformationDTO.getMaxRentDaysAtRentTime());
        rentInformation.setGameCopy(gameCopyRepository.getFirstById(
            UUID.fromString(rentInformationDTO.getGameCopyId())
        ));
        if(!rentInformationDTO.getUserId().isBlank())
            rentInformation.setUser(userRepository.getFirstById(UUID.fromString(rentInformationDTO.getUserId())));
        rentInformation.setPriceAtRentTime(gamePriceConverter.fromDto(rentInformationDTO.getPriceAtRentTime()));
        rentInformation.setTimeOfRent(timestampConverter.fromDto(rentInformationDTO.getTimestampOfRent()));
        rentInformation.setTimeOfReturn(timestampConverter.fromDto(rentInformationDTO.getTimestampOfReturn()));
        return rentInformation;
    }
}
