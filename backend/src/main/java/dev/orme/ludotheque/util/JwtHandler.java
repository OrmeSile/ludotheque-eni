package dev.orme.ludotheque.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtHandler {
    @Value("jwt.key")
    private static SecretKey jwtKey;
    @Value("jwt.expiration.time")
    private static long expirationTime;

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(jwtKey)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(jwtKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
