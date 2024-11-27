package dev.orme.ludotheque.data.entity;

import dev.orme.ludotheque.data.POJO.RoleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    public User(String username, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.roles.add(new Role(RoleType.ROLE_USER));
    }

    public User(String username, String password, String id, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.id = id;
        if(roles == null) {
            var defaultRole = new Role(RoleType.ROLE_USER);
            this.roles.add(defaultRole);
        }else {
            this.roles = roles;
        }
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles.add(new Role(RoleType.ROLE_USER));
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }
}
