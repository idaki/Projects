package bg.softuni.userservice.models.entity.business.company;

import bg.softuni.userservice.models.Password;
import jakarta.persistence.*;

@Entity
@Table(name="platform_company_credentials")
public class PasswordCompany implements Password {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Company user;

    private String hashedPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getUser() {
        return user;
    }

    public void setUser(Company user) {
        this.user = user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}