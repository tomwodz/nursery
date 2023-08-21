package pl.tomwodz.nursery.controllers.errors;

public class ChildNotFoundException extends RuntimeException {
    public ChildNotFoundException(String message) {
        super(message);
    }
}
