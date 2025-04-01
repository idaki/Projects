package bg.softuni.communicationservice.service;

public interface EmailService {
    void sendSimpleMessage(
            String to, String subject, String text);
}
