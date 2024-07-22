package bg.softuni.userservice.utils;

import bg.softuni.userservice.models.entity.user.User;
import org.springframework.context.ApplicationEvent;

public class UserDeleteEvent extends ApplicationEvent {
    private final User user;

    public UserDeleteEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}