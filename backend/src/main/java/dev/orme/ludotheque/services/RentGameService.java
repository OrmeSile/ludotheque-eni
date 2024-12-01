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
}
