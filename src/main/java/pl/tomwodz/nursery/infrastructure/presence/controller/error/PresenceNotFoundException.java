package pl.tomwodz.nursery.infrastructure.presence.controller.error;

public class PresenceNotFoundException extends RuntimeException {
    public PresenceNotFoundException(String message) {
        super(message);
    }
}
