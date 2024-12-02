package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.util.SortedSet;
import java.util.UUID;

@Entity(name = "app_user")
public class User {

    @Id
    private UUID id;


    private boolean isActive = false;

    @Column(unique = true)
    private String mail;
    private String givenName;
    private String familyName;
    @Column(unique = true, nullable = false)
    private String username;
    @OneToMany
    private SortedSet<Game> games;

    public User() {}

    public User(String username) {
        this.id = UUID.randomUUID();
        this.username = username;
    }

    public User(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.isActive = true;
    }

    public User(UUID id, String mail, String username) {
        this.id = id;
        this.isActive = true;
        this.mail = mail;
        this.username = username;
    }

    public User(UUID uuid, String username, String email, String givenName, String familyName) {
        this.id = uuid;
        this.username = username;
        this.mail = email;
        this.givenName = givenName;
        this.familyName = familyName;
        this.isActive = true;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
