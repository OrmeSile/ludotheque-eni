package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@Entity
public class Genre implements Comparable<Genre>{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "genres")
    private SortedSet<Game> games = new TreeSet<>();

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {

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

    public SortedSet<Game> getGames() {
        return games;
    }

    public void setGames(SortedSet<Game> games) {
        this.games = games;
    }

    @Override
    public int compareTo(Genre o) {
        return this.name.compareTo(o.name);
    }
}
