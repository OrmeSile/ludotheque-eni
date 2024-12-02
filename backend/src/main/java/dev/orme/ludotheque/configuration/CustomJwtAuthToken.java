package dev.orme.ludotheque.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthToken extends JwtAuthenticationToken {
    private final String username;
    private final String givenName;
    private final String familyName;
    private final String email;

    public CustomJwtAuthToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String username, String givenName, String familyName, String email) {
        super(jwt, authorities);
        this.username = username;
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }
}
