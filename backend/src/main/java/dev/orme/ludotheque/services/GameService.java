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

    public Game createGame(Game game) throws NotCreatedException {
        if(gameRepository.existsById(game.getId())) {
            throw new NotCreatedException("game", game.getId());
        }
        try {
            return this.gameRepository.save(game);
        }catch (IllegalArgumentException e){
            throw new NotCreatedException("game", game.getId());
        }
    }

    public Game getGame(UUID uuid) throws NotFoundException {
        var game = this.gameRepository.findById(uuid);
        if(game.isEmpty()) {
            throw new NotFoundException("game", uuid);
        }
        return game.get();
    }

    public Page<Game> getGamePageWithSize(int page, int size) throws NotFoundException {
        var pageResponse = gameRepository.findAll(PageRequest.of(page, size));
        if(pageResponse.hasContent())
            return pageResponse;
        throw new NotFoundException("game");
    }

    public Game updateGame (UUID id, Game game) throws NotUpdatedException, NotFoundException {
        if(id == null || !gameRepository.existsById(id))
            throw new NotFoundException("game", id);
        if(game.getId() == null) {
            game.setId(id);
        }
        try {
            return gameRepository.save(game);
        }catch (Exception e){
            throw new NotUpdatedException("game", id);
        }
    }

    public void deleteGame(UUID id) throws NotFoundException {
        if(gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("game", id);
    }
}
