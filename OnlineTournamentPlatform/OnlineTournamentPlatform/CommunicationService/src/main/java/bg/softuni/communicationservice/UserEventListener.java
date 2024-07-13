package bg.softuni.communicationservice;

import org.springframework.context.event.EventListener;

public class UserEventListener {
    private EmailService emailService;

    @EventListener
    public void handleUserEvent(UserEvent event) {
        switch (event.getEventType()) {
            case "CONFIRM_EMAIL":
                emailService.sendEmail(event.getEmail(), "noreply@yourdomain.com", "Confirm Your Email", "Please click on the link to confirm your email.");
                break;
            case "PASSWORD_CHANGE":
                emailService.sendEmail(event.getEmail(), "noreply@yourdomain.com", "Password Changed", "Your password has been changed successfully.");
                break;
            case "ACCOUNT_DELETED":
                emailService.sendEmail(event.getEmail(), "noreply@yourdomain.com", "Account Deleted", "Your account has been deleted.");
                break;
            case "SUBSCRIBE_NEWSLETTER":
                emailService.sendEmail(event.getEmail(), "noreply@yourdomain.com", "Newsletter Subscription", "Thank you for subscribing to our newsletter!");
                break;
        }
    }
}
