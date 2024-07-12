package bg.softuni.authenticationservice.model;


import bg.softuni.authenticationservice.model.enums.TokenType;
import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue
    public long id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}