package pl.tomwodz.nursery.exception.validation;

public class AddressValidationException extends RuntimeException {
    public AddressValidationException(String message) {
        super(message);
    }
}
