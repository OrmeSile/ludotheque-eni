package dev.orme.ludotheque.configuration;

import com.sun.source.util.Trees;
import dev.orme.ludotheque.entities.Game;
import dev.orme.ludotheque.entities.Genre;
import dev.orme.ludotheque.repositories.GameRepository;
import dev.orme.ludotheque.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    GenreRepository genreRepository;
    GameRepository gameRepository;
    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    public DatabaseInitializer(GenreRepository genreRepository, GameRepository gameRepository) {
        this.genreRepository = genreRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] genres = {
                "Abstract Strategy",
                "Action / Dexterity",
                "Adventure",
                "Age of Reason",
                "American Civil War",
                "American Indian Wars",
                "American Revolutionary War",
                "American West",
                "Ancient",
                "Animals",
                "Arabian",
                "Aviation / Flight",
                "Bluffing",
                "Book",
                "Card Game",
                "Children's Game",
                "City Building",
                "Civil War",
                "Civilization",
                "Collectible Components",
                "Comic Book / Strip",
                "Deduction",
                "Dice",
                "Economic",
                "Educational",
                "Electronic",
                "Environmental",
                "Expansion for Base-game",
                "Exploration",
                "Fan Expansion",
                "Fantasy",
                "Farming",
                "Fighting",
                "Game System",
                "Horror",
                "Humor",
                "Industry / Manufacturing",
                "Korean War",
                "Mafia",
                "Math",
                "Mature / Adult",
                "Maze",
                "Medical",
                "Medieval",
                "Memory",
                "Miniatures",
                "Modern Warfare",
                "Movies / TV / Radio theme",
                "Murder / Mystery",
                "Music",
                "Mythology",
                "Napoleonic",
                "Nautical",
                "Negotiation",
                "Novel-based",
                "Number",
                "Party Game",
                "Pike and Shot",
                "Pirates",
                "Political",
                "Post-Napoleonic",
                "Prehistoric",
                "Print & Play",
                "Puzzle",
                "Racing",
                "Real-time",
                "Religious",
                "Renaissance",
                "Science Fiction",
                "Space Exploration",
                "Spies / Secret Agents",
                "Sports",
                "Territory Building",
                "Trains",
                "Transportation",
                "Travel",
                "Trivia",
                "Video Game Theme",
                "Vietnam War",
                "Wargame",
                "Word Game",
                "World War I",
                "World War II",
                "Zombies"};
        if (genreRepository.count() == genres.length) {
            return;
        }
        Genre[] savedGenres = new Genre[genres.length];
        for(int i = 0; i < genres.length; i++) {
            savedGenres[i] = genreRepository.save(new Genre(genres[i]));
        }
        Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
        Random random = new Random();
        gameRepository.findAll().forEach(game -> {
            if(game.getGenres().isEmpty()){
                var quantityOfGenresToAdd = random.nextInt(1,3) + 1;
                var genresToAdd = new TreeSet<Genre>();
                for (int i = 0; i < quantityOfGenresToAdd; i++) {
                     Genre gameGenre = savedGenres[random.nextInt(0,savedGenres.length)];
                     if(genresToAdd.contains(gameGenre))
                         continue;
                     genresToAdd.add(gameGenre);
                }
                game.setGenres(genresToAdd);
                logger.warn(gameRepository.save(game).getGenres().toString());
        }});
    }
}
