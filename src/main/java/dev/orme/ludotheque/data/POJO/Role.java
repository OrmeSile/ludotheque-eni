package dev.orme.ludotheque.data.POJO;

import dev.orme.ludotheque.data.entity.User;
import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Roles role;

    @ManyToOne
    @JoinColumn
    private User user;

    public Role(){}

    public Role(Roles role, User user) {
        this.role = role;
        this.user = user;
    }

    public Role(Roles role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public Roles getName() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

