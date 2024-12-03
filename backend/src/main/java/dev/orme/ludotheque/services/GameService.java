package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.RentInformationRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    UserRepository userRepository;
    GameRepository gameRepository;
    RentInformationRepository rentInformationRepository;

    public GameService(UserRepository userRepository, GameRepository gameRepository, RentInformationRepository rentInformationRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.rentInformationRepository = rentInformationRepository;
    }

    public Game createGame(Game game) {
        return this.gameRepository.save(game);
    }

    public Page<Game> getGamePageWithSize(int page, int size) {
        return gameRepository.findAll(PageRequest.of(page, size));

    }
}
