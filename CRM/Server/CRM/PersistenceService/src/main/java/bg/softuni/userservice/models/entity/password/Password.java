package bg.softuni.userservice.models.entity.password;

import bg.softuni.userservice.models.entity.user.UserSecurity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passwords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    @Column
    private LocalDateTime passwordSetDate;

    @Column
    private String resetPasswordToken;

    @Column
    private Boolean isActiveResetPasswordToken;

    @Column
    private LocalDateTime resetPasswordTokenExpiryDate;

    @OneToOne(mappedBy = "password")
    private UserSecurity userSecurity;
}
