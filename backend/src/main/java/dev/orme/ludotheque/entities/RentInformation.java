package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "rent_information")
public class RentInformation implements Comparable<RentInformation> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int daysRented = 0;
    private int  maxRentDaysAtRentTime = 0;
    private ZonedDateTime timeOfRent;
    private ZonedDateTime timeOfReturn;
    @Enumerated(EnumType.STRING)
    private WearStatus wearStatus;
    @ManyToOne
    private GameCopy gameCopy;
    @ManyToOne
    private GamePrice priceAtRentTime;

    @ManyToOne
    private User user;

    public RentInformation(int daysRented,
                           int maxRentDaysAtRentTime,
                           WearStatus wearStatus,
                           GameCopy gameCopy,
                           User user,
                           GamePrice priceAtRentTime,
                           ZonedDateTime timeOfRent,
                           ZonedDateTime timeOfReturn) {
        this.daysRented = daysRented;
        this.maxRentDaysAtRentTime = maxRentDaysAtRentTime;
        this.timeOfRent = timeOfRent;
        this.wearStatus = wearStatus;
        this.gameCopy = gameCopy;
        this.priceAtRentTime = priceAtRentTime;
        this.user = user;
        this.timeOfReturn = timeOfReturn;
    }

    public RentInformation(UUID id,
                           int daysRented,
                           int maxRentDaysAtRentTime,
                           WearStatus wearStatus,
                           GameCopy gameCopy,
                           User user,
                           GamePrice priceAtRentTime,
                           ZonedDateTime timeOfRent,
                           ZonedDateTime timeOfReturn) {
        this.id = id;
        this.daysRented = daysRented;
        this.maxRentDaysAtRentTime = maxRentDaysAtRentTime;
        this.wearStatus = wearStatus;
        this.timeOfRent = timeOfRent;
        this.gameCopy = gameCopy;
        this.priceAtRentTime = priceAtRentTime;
        this.user = user;
        this.timeOfReturn = timeOfReturn;
    }

    public RentInformation() {
    }

    @Override
    public int compareTo(RentInformation o) {
        return timeOfRent.compareTo(o.timeOfRent);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public int getMaxRentDaysAtRentTime() {
        return maxRentDaysAtRentTime;
    }

    public void setMaxRentDaysAtRentTime(int maxRentDaysAtRentTime) {
        this.maxRentDaysAtRentTime = maxRentDaysAtRentTime;
    }

    public ZonedDateTime getTimeOfRent() {
        return timeOfRent;
    }

    public void setTimeOfRent(ZonedDateTime timeOfRent) {
        this.timeOfRent = timeOfRent;
    }

    public GamePrice getPriceAtRentTime() {
        return priceAtRentTime;
    }

    public void setPriceAtRentTime(GamePrice priceAtRentTime) {
        this.priceAtRentTime = priceAtRentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getTimeOfReturn() {
        return timeOfReturn;
    }

    public void setTimeOfReturn(ZonedDateTime timeOfReturn) {
        this.timeOfReturn = timeOfReturn;
    }

    public GameCopy getGameCopy() {
        return gameCopy;
    }

    public void setGameCopy(GameCopy gameCopy) {
        this.gameCopy = gameCopy;
    }

    public WearStatus getWearStatus() {
        return wearStatus;
    }

    public void setWearStatus(WearStatus wearStatus) {
        this.wearStatus = wearStatus;
    }
}
