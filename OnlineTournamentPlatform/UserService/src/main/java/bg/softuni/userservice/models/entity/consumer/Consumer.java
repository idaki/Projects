package bg.softuni.userservice.models.entity.consumer;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

@Entity
@Table(name = "platform_consumer_users")
public class Consumer extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", referencedColumnName = "id")
    private UserPassword password;

    public UserPassword getPassword() {
        return password;
    }

    public void setPassword(UserPassword password) {
        this.password = password;
    }
}