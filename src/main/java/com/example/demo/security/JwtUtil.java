package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;


@Component
public class JwtUtil {


    private final String SECRET = "secret_key_123_secret_key_123_secret_key_123";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // private final String SECRET = "secret_key_123secret_key_123";
    // private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractUsername(token);
        return extractedEmail.equals(email);
    }
}
