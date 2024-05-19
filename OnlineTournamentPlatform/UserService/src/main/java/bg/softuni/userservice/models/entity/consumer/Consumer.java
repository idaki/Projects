package bg.softuni.userservice.models.entity.consumer;

import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "consumers")
public class Consumer extends User {

}
