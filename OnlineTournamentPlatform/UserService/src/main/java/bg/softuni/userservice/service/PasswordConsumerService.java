package bg.softuni.userservice.service;


import bg.softuni.userservice.models.entity.consumer.Consumer;

public interface PasswordConsumerService  {
    void savePassword(Consumer user, String password);
}
