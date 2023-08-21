package pl.tomwodz.nursery.controllers.errors;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
