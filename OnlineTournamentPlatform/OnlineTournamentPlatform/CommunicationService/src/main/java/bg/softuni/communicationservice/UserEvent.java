package bg.softuni.communicationservice;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {
    private String email;
    private String eventType;

    public UserEvent(Object source, String email, String eventType) {
        super(source);
        this.email = email;
        this.eventType = eventType;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getEventType() {
        return eventType;
    }
}
