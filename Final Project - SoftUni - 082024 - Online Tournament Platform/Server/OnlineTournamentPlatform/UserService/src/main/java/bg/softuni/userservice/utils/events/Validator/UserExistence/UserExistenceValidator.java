package bg.softuni.userservice.utils.events.Validator.UserExistence;

public interface UserExistenceValidator {

    void checkIfUsernameExists(String username);

    void checkIfEmailExists(String email);
}
