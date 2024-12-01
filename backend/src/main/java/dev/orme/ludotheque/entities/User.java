package dev.orme.ludotheque.entities;

import dev.orme.ludotheque.objects.RoleType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean isActive = false;
    private String mail;
    @Column(unique = true, nullable = false)
    private String username;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public User() {}

    public User(String username) {
        this.username = username;
        this.role = new Role(RoleType.ROLE_USER);
    }

    public User(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public User(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.role = new Role(RoleType.ROLE_USER);
    }

    public User(UUID id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User(UUID id, String username, String mail, boolean isActive) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
