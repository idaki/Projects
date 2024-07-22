package bg.softuni.authenticationservice.service.impl;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.repository.TokenRepository;
import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.userservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    @Value("${jwt.secretkey}")
    private String secretKey;

    private long expirationTime = 1000 * 60 * 60 * 1; // in milliseconds

    public JwtServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generateToken(String username) {
        String token = Jwts.builder().
                subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigninKey())
                .compact();

        saveTokenToDB(username, token);

        return token;
    }

    private void saveTokenToDB(String username, String token) {
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setUser(userRepository.findByUsername(username).get());
        newToken.setExpired(false);
        newToken.setRevoked(false);
        tokenRepository.save(newToken);
    }

    @Override
    public Boolean validateToken(String token, String username) {
        final String usernameFromToken = extractClaim(token, Claims::getSubject);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String generateTokenAfterPasswordUpdate(String token) {
        String username = extractClaim(token, Claims::getSubject);
        return this.generateToken(username);
    }

    @Override
    public String extractUsername(String token) {

        return this.extractAllClaims(token).getSubject();
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
