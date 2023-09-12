package pl.tomwodz.nursery.infrastructure.user.controller.error;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
