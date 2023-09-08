package pl.tomwodz.nursery.infrastructure.information.controller.error;

public class InformationNotFoundException extends RuntimeException {
    public InformationNotFoundException(String message) {
        super(message);
    }
}
