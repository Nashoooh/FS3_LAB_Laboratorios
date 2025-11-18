package com.ignacio.fs3_lab_laboratorios.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // IMPORTANTE: Este secret debe ser EXACTAMENTE el mismo que en el microservicio de usuarios
    private static final String SECRET_KEY = "mySecretKeyForJWT2024ThisIsAVeryLongSecretKeyThatMeetsRequirements";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Integer extractUserId(String token) {
        return extractAllClaims(token).get("userId", Integer.class);
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    public Integer extractRolId(String token) {
        return extractAllClaims(token).get("rolId", Integer.class);
    }

    public String extractRolNombre(String token) {
        return extractAllClaims(token).get("rolNombre", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            System.out.println("Token validado correctamente. Usuario: " + claims.get("email"));
            System.out.println("Expira: " + claims.getExpiration());
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            System.err.println("Error al validar token: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
