package pl.tomwodz.nursery.exception;

public class PresenceNotFoundException extends RuntimeException {
    public PresenceNotFoundException(String message) {
        super(message);
    }
}
