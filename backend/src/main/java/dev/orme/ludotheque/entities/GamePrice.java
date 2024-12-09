package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name="game_price")
public class GamePrice implements Comparable<GamePrice> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private float price = 0.0f;
    private ZonedDateTime timeOfPriceSet;
    @ManyToOne
    private RentInformation rentInformation;
    @ManyToOne
    private GameCopy gameCopy;


    @Override
    public int compareTo(GamePrice gamePrice) {
        return this.timeOfPriceSet.compareTo(gamePrice.timeOfPriceSet);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ZonedDateTime getTimeOfPriceSet() {
        return timeOfPriceSet;
    }

    public void setTimeOfPriceSet(ZonedDateTime timeOfPriceSet) {
        this.timeOfPriceSet = timeOfPriceSet;
    }

    public GameCopy getGameCopy() {
        return gameCopy;
    }

    public void setGameCopy(GameCopy gameCopy) {
        this.gameCopy = gameCopy;
    }

    public RentInformation getRentInformation() {
        return rentInformation;
    }

    public void setRentInformation(RentInformation rentInformation) {
        this.rentInformation = rentInformation;
    }
}
