package pl.tomwodz.nursery.exception.validation;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String message) {
        super(message);
    }
}
