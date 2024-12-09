package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        try {
            return this.gameRepository.save(game);
        }catch (IllegalArgumentException e){
            throw new NotCreatedException(Game.class);
        }
    }

    public Game getGame(UUID uuid) throws NotFoundException {
        var game = this.gameRepository.findById(uuid);
        if(game.isEmpty()) {
            throw new NotFoundException(Game.class);
        }
        return game.get();
    }

    public Page<Game> getGamePageWithSize(int page, int size) {
        return gameRepository.findAll(PageRequest.of(page, size));
    }

    public Game updateGame (UUID id, Game game) {
        if(game.getId() == null) {
            game.setId(id);
        }
        try {
            return gameRepository.save(game);
        }catch (IllegalArgumentException e){
            throw new NotUpdatedException(Game.class);
        }
    }

    public void deleteGame(UUID id) {
        if(gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return;
        }
        throw new NotDeletedException(Game.class);
    }
}
