package bg.softuni.userservice.models.entity.user;

import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

@Entity
@Table(name = "users_users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "is_logged", nullable = false)
    private Boolean isLogged;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", referencedColumnName = "id")
    private UserPassword password;


    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.isLogged = false;
    }

    public User() {
        this.isLogged = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Boolean isLogged) {
        this.isLogged = isLogged;
    }
}