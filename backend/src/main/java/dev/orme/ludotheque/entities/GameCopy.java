package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@Entity
public class GameCopy implements Comparable<GameCopy> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Game game;
    @Transient
    private RentInformation currentRentInformation;
    @Transient
    private GamePrice currentGamePrice;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gameCopy")
    private SortedSet<RentInformation> rentInformations = new TreeSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gameCopy")
    private SortedSet<GamePrice> gamePrices = new TreeSet<>();
    @Enumerated(EnumType.STRING)
    private WearStatus wearStatus;
    private boolean isRented;
    private boolean isActive;
    private int maxRentDays = 0;

    public GameCopy() {
    }

    public GameCopy(UUID id, Game game, RentInformation currentRentInformation, GamePrice currentGamePrice, SortedSet<RentInformation> rentInformations, SortedSet<GamePrice> gamePrices, WearStatus wearStatus, boolean isRented, boolean isActive) {
        this.id = id;
        this.game = game;
        this.currentRentInformation = currentRentInformation;
        this.currentGamePrice = currentGamePrice;
        this.rentInformations = rentInformations;
        this.gamePrices = gamePrices;
        this.wearStatus = wearStatus;
        this.isRented = isRented;
        this.isActive = isActive;
    }

    @Override
    public int compareTo(GameCopy o) {
        return this.currentRentInformation.getTimeOfRent().compareTo(o.currentRentInformation.getTimeOfRent());
    }

    public int getMaxRentDays() {
        return maxRentDays;
    }

    public void setMaxRentDays(int maxRentDays) {
        this.maxRentDays = maxRentDays;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public RentInformation getCurrentRentInformation() {
        return currentRentInformation;
    }

    public void setCurrentRentInformation(RentInformation currentRentInformation) {
        this.currentRentInformation = currentRentInformation;
    }

    public GamePrice getCurrentGamePrice() {
        return currentGamePrice;
    }

    public void setCurrentGamePrice(GamePrice currentGamePrice) {
        this.currentGamePrice = currentGamePrice;
    }

    public SortedSet<RentInformation> getRentInformations() {
        return rentInformations;
    }

    public void setRentInformations(SortedSet<RentInformation> rentInformations) {
        this.rentInformations = rentInformations;
    }

    public SortedSet<GamePrice> getGamePrices() {
        return gamePrices;
    }

    public void setGamePrices(SortedSet<GamePrice> gamePrices) {
        this.gamePrices = gamePrices;
    }

    public WearStatus getWearStatus() {
        return wearStatus;
    }

    public void setWearStatus(WearStatus wearStatus) {
        this.wearStatus = wearStatus;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
