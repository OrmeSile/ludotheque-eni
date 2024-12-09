package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@Entity(name = "game")
public class Game implements Comparable<Game> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private int yearPublished;
    private ZonedDateTime timeOfCreation;
    @ManyToMany(fetch = FetchType.EAGER)
    private SortedSet<Genre> genres = new TreeSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private SortedSet<GameCopy> copies = new TreeSet<>();


    public Game() {
    }

    public Game(String name, String description, ZonedDateTime timeOfCreation) {
        this.name = name;
        this.description = description;
        this.timeOfCreation = timeOfCreation;
    }

    public Game(UUID id, String name, String description,
                ZonedDateTime timeOfCreation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeOfCreation = timeOfCreation;
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

    public ZonedDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(ZonedDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearCreated) {
        this.yearPublished = yearCreated;
    }

    public SortedSet<Genre> getGenres() {
        return genres;
    }

    public void setGenres(SortedSet<Genre> genres) {
        this.genres = genres;
    }

    public SortedSet<GameCopy> getCopies() {
        return copies;
    }

    public void setCopies(SortedSet<GameCopy> copies) {
        this.copies = copies;
    }

    @Override
    public int compareTo(Game o) {
        return this.name.compareTo(o.name);
    }
}
