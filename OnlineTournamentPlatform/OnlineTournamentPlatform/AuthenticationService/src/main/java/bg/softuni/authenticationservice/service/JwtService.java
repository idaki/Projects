package bg.softuni.authenticationservice.service;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {
    public abstract String generateToken(String username);

    public abstract Boolean validateToken(String token, String username);

    public abstract <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public Claims extractAllClaims(String token);
}
