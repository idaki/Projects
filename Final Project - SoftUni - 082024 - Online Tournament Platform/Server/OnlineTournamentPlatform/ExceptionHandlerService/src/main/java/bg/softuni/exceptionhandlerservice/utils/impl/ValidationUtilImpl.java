package bg.softuni.exceptionhandlerservice.utils.impl;

import bg.softuni.exceptionhandlerservice.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> getViolations(E entity) {
        return validator.validate(entity);
    }

    @Override
    public <E> String getFormattedErrorMessage(Set<ConstraintViolation<E>> violations) {
        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<E> violation : violations) {
            errorMessage.append(violation.getMessage()).append("; ");
        }
        return errorMessage.toString().trim();
    }
}
