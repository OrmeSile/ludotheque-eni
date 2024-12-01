package dev.orme.ludotheque.entities;

import dev.orme.ludotheque.objects.RoleType;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private RoleType role;
    @OneToMany(mappedBy = "role")
    private Collection<User> userEntities = new HashSet<>();

    public Role(){}

    public Role(RoleType role) {
        this.role = role;
    }

    public Role(RoleType role, User users) {
        this.role = role;
        this.userEntities.add(users);
    }

    public UUID getId() {
        return id;
    }

    public RoleType getName() {
        return role;
    }

    public Collection<User> getUsers() {
        return userEntities;
    }
}

