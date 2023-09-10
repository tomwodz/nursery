package pl.tomwodz.nursery.infrastructure.user.controller.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
