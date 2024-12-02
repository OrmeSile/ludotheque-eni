package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@Entity(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;
    private String description;
    private boolean isRented = false;
    private boolean isActive = false;
    private int yearPublished;
    private ZonedDateTime timeOfCreation;
    private int maxRentDays = 0;

    @ManyToOne()
    private User renter;

    @Transient
    private GamePrice gamePrice;
    @Transient
    private RentInformation currentRentInformation;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private SortedSet<GamePrice> prices =  new TreeSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private SortedSet<RentInformation> rentInformations = new TreeSet<>();

    public Game() {
    }

    public Game(String name, String description, boolean isRented, boolean isActive, ZonedDateTime timeOfCreation, User renter) {
        this.name = name;
        this.description = description;
        this.isRented = isRented;
        this.isActive = isActive;
        this.timeOfCreation = timeOfCreation;
        this.renter = renter;
        this.gamePrice = prices.isEmpty() ? null : prices.last();
        this.currentRentInformation = prices.isEmpty() ? null : rentInformations.first();
    }

    public Game(UUID id, String name, String description, boolean isRented, boolean isActive,
                ZonedDateTime timeOfCreation, User renter ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isRented = isRented;
        this.isActive = isActive;
        this.timeOfCreation = timeOfCreation;
        this.renter = renter;
        this.gamePrice = prices.isEmpty() ? null : prices.last();
        this.currentRentInformation = prices.isEmpty() ? null : rentInformations.first();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ZonedDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(ZonedDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public GamePrice getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(GamePrice gamePrice) {
        this.gamePrice = gamePrice;
    }

    public RentInformation getCurrentRentInformation() {
        return currentRentInformation;
    }

    public void setCurrentRentInformation(RentInformation currentRentInformation) {
        this.currentRentInformation = currentRentInformation;
    }

    public SortedSet<GamePrice> getPrices() {
        return prices;
    }

    public void setPrices(SortedSet<GamePrice> prices) {
        this.prices = prices;
    }

    public SortedSet<RentInformation> getRentInformations() {
        return rentInformations;
    }

    public void setRentInformations(SortedSet<RentInformation> rentInformations) {
        this.rentInformations = rentInformations;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearCreated) {
        this.yearPublished = yearCreated;
    }
}
