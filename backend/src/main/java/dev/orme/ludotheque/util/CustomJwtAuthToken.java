package dev.orme.ludotheque.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthToken extends JwtAuthenticationToken {
    private String username;
    private String givenName;
    private String familyName;
    private String email;

    public CustomJwtAuthToken(Jwt jwt) {
        super(jwt);
    }

    public CustomJwtAuthToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

    public CustomJwtAuthToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name) {
        super(jwt, authorities, name);
    }

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
