package dev.orme.ludotheque.services;

import dev.orme.ludotheque.entities.GameCopy;
import dev.orme.ludotheque.entities.RentInformation;
import dev.orme.ludotheque.repositories.GameCopyRepository;
import dev.orme.ludotheque.repositories.NotFoundException;
import dev.orme.ludotheque.repositories.RentInformationRepository;
import dev.orme.ludotheque.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class RentService {
    UserRepository userRepository;
    GameCopyRepository gameCopyRepository;
    RentInformationRepository rentInformationRepository;

    public RentService(UserRepository userRepository, GameCopyRepository gameCopyRepository, RentInformationRepository rentInformationRepository) {
        this.userRepository = userRepository;
        this.gameCopyRepository = gameCopyRepository;
        this.rentInformationRepository = rentInformationRepository;
    }

    @Transactional
    public GameCopy rentGame(UUID renterId, UUID gameCopyId) throws NotFoundException {
        var user = userRepository.findById(renterId);
        if (user.isEmpty())
            throw new NotFoundException("user", renterId);
        if (!user.get().isActive())
            throw new UserNotActiveException(user.get());
        var gameCopy = gameCopyRepository.findById(gameCopyId);
        if (gameCopy.isEmpty())
            throw new NotFoundException("game copy", gameCopyId);
        if (!gameCopy.get().isActive()
                || gameCopy.get().isRented()
                || gameCopy.get().getTimeOfStockEntry().isAfter(ZonedDateTime.now())
                || gameCopy.get().getCurrentGamePrice() == null
        ) {
            throw new NotRentableException(gameCopy.get());
        }
        var rentInformation = new RentInformation(
                0,
                gameCopy.get().getMaxRentDays(),
                gameCopy.get().getWearStatus(),
                gameCopy.get(),
                user.get(),
                gameCopy.get().getCurrentGamePrice(),
                ZonedDateTime.now(),
                null
        );
        var savedRentInformation = rentInformationRepository.save(rentInformation);
        gameCopy.get().addRentInformation(savedRentInformation);
        gameCopy.get().setRented(true);
        return gameCopyRepository.save(gameCopy.get());
    }
}
