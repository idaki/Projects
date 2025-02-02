package bg.softuni.userservice.utils.Validator.UserRegisterDTO;

import bg.softuni.userservice.models.dto.UserRegisterDTO;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserRegisterDTOValidatorImpl implements UserRegisterDTOValidator {

    private final Validator validator;

   // Inject the UserService

    UserRegisterDTOValidatorImpl (Validator validator) {
        this.validator = validator;

    }

    @Override
    public void validate(UserRegisterDTO registerDTO) {
        // Step 1: Validate the DTO using Jakarta Bean Validation
        Set<ConstraintViolation<UserRegisterDTO>> violations = validator.validate(registerDTO);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            throw new IllegalArgumentException("Validation failed: " + errorMessage);
        }

    }

}
