package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.RentInformation;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.RentInformationRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class RentGameService {

    UserRepository userRepository;
    GameRepository gameRepository;
    RentInformationRepository rentInformationRepository;

    public RentGameService(UserRepository userRepository, GameRepository gameRepository, RentInformationRepository rentInformationRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.rentInformationRepository = rentInformationRepository;
    }

    public ServiceResponse<RentInformation> rentGame(UUID userId, UUID gameId){
        var user = userRepository.findById(userId);
        if(user.isEmpty())
            return new ServiceResponse<>(ResponseStatus.ERROR, null, "User does not exist.");
        if(!user.get().isActive())
            return new ServiceResponse<>(ResponseStatus.ERROR, null, "User is not active.");
        var game = gameRepository.findById(gameId);
        if(game.isEmpty())
            return new ServiceResponse<>(ResponseStatus.ERROR, null, "Game does not exist.");
        if(!game.get().isActive())
            return new ServiceResponse<>(ResponseStatus.ERROR, null, "Game is not active.");

        var rentInformation = new RentInformation(
                null,
                0,
                game.get().getMaxRentDays(),
                ZonedDateTime.now(),
                game.get(),
                game.get().getGamePrice()
                );
        var savedRentInformation = rentInformationRepository.save(rentInformation);
        return new ServiceResponse<>(ResponseStatus.OK, savedRentInformation, null);
    }
}
