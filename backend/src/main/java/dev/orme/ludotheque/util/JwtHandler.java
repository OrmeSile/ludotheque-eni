package dev.orme.ludotheque.util;

import dev.orme.ludotheque.services.UserService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtHandler implements Converter<org.springframework.security.oauth2.jwt.Jwt, AbstractAuthenticationToken> {

    private UserService userService;

    public JwtHandler() {}

    public JwtHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        String id = source.getSubject();
        String userGivenName = source.getClaim("given_name");
        String userFamilyName = source.getClaim("family_name");
        String userUsername = source.getClaim("upn");
        String userEmail = source.getClaim("email");
        var resultingToken = new CustomJwtAuthToken(
                source,
                Stream.concat(
                        new JwtGrantedAuthoritiesConverter()
                                .convert(source)
                                .stream(),
                        extractResourceRoles(source)
                                .stream())
                        .collect(Collectors.toSet()),
                userGivenName,
                userFamilyName,
                userUsername,
                userEmail
        );
        userService.insertUserIfNotPresent(id, userUsername, userEmail, userGivenName, userFamilyName);
        return resultingToken;
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt source) {
        var resourceAccess = new HashMap<>(source.getClaim("resource_access"));
        @SuppressWarnings("unchecked") var ludotheque = (Map<String, List<String>>) resourceAccess.get(
                "ludotheque-client");
        var roles = (ArrayList<String>) ludotheque.get("roles");

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s",
                        role.replace("-", "_").toUpperCase(Locale.ROOT))))
                .collect(Collectors.toSet());
    }
}
