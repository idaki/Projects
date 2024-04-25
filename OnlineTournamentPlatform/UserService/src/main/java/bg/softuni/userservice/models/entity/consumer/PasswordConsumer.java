package bg.softuni.userservice.models.entity.consumer;

import bg.softuni.userservice.models.Password;
import jakarta.persistence.*;

@Entity
@Table(name="platform_consumer_credentials")
public class PasswordConsumer implements Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Consumer user;

    private String hashedPassword;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consumer getUser() {
        return user;
    }

    public void setUser(Consumer user) {
        this.user = user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


}
