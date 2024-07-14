package bg.softuni.authenticationservice.service.impl;

import bg.softuni.authenticationservice.model.Token;
import bg.softuni.authenticationservice.repositry.TokenRepository;
import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.userservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl extends JwtService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
//    @Value("${jwt.secret}")
    private String secretKey = "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";

//    @Value("${jwt.expiration}")
    private long expirationTime = 1000*60*60*1; // in milliseconds

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
        newToken.setUser(userRepository.findByUsername(username).get());  // Assume UserService can find a user by username
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
protected Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
