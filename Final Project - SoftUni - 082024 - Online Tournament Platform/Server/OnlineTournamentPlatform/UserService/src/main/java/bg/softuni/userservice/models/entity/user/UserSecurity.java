package bg.softuni.userservice.models.entity.user;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.password.Password;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_security")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "password_id", referencedColumnName = "id")
    private Password password;

    @OneToMany(mappedBy = "userSecurity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Token> tokens = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
