package pl.tomwodz.nursery.exception;

public class ChildNotFoundException extends RuntimeException {
    public ChildNotFoundException(String message) {
        super(message);
    }
}
