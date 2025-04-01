package bg.softuni.exceptionhandlerservice.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    <E> boolean isValid(E entity);
    <E> Set<ConstraintViolation<E>> getViolations(E entity);
    <E> String getFormattedErrorMessage(Set<ConstraintViolation<E>> violations);
}
