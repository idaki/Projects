package bg.softuni.authenticationservice.service;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public abstract class JwtService {
    public abstract String generateToken(String username);

    public abstract Boolean validateToken(String token, String username);

    public abstract <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    protected abstract Claims extractAllClaims(String token);
}
