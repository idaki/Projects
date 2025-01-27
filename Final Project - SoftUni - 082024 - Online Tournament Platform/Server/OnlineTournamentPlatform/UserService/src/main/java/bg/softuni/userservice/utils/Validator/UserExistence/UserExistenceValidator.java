package bg.softuni.userservice.utils.Validator.UserExistence;

public interface UserExistenceValidator {

    void checkIfUsernameExists(String username);

    void checkIfEmailExists(String email);
}
