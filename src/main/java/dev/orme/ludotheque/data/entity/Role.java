package dev.orme.ludotheque.data.entity;

import dev.orme.ludotheque.data.POJO.RoleType;
import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private RoleType role;
    @ManyToOne
    private User user;

    public Role(){}

    public Role(RoleType role) {
        this.role = role;
    }

    public Role(RoleType role, User user) {
        this.role = role;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public RoleType getName() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

