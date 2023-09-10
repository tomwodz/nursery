package pl.tomwodz.nursery.infrastructure.validator.error;

public class ChildValidationException extends RuntimeException {
    public ChildValidationException(String message) {
        super(message);
    }
}
