package bg.softuni.communicationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public UserEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(String email, String type) {
        UserEvent event = new UserEvent(this, email, type);
        eventPublisher.publishEvent(event);
    }
}