package pl.tomwodz.nursery.infrastructure.child.controller.error;

public class ChildNotFoundException extends RuntimeException {
    public ChildNotFoundException(String message) {
        super(message);
    }
}
