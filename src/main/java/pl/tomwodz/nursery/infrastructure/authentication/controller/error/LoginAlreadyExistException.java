package pl.tomwodz.nursery.infrastructure.authentication.controller.error;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
