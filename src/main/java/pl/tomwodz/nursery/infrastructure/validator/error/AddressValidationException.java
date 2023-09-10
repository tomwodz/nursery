package pl.tomwodz.nursery.infrastructure.validator.error;

public class AddressValidationException extends RuntimeException {
    public AddressValidationException(String message) {
        super(message);
    }
}
