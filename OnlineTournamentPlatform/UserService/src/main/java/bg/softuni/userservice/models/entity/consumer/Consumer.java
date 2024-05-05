package bg.softuni.userservice.models.entity.consumer;


import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.entity.password.UserPassword;
import jakarta.persistence.*;

@Entity
@Table(name = "consumers")
public class Consumer extends User {


}