package pl.tomwodz.nursery.validatros;

import pl.tomwodz.nursery.exception.validation.AddressValidationException;
import pl.tomwodz.nursery.model.Address;

public class AddressValidator {


    public static void validateStreet(String street) {
        String regex = "^.{3,}$";
        if (!street.matches(regex)) {
            throw new AddressValidationException();
        }
    }

    public static void validateZipCode(String zipCode) {
        String regex = "([0-9]{2}-[0-9]{3})";
        if (!zipCode.matches(regex)) {
            throw new AddressValidationException();
        }
    }

    public static void validateCity(String city) {
        String regex = "^.{3,}$";
        if (!city.matches(regex)) {
            throw new AddressValidationException();
        }
    }

    public static void validatorAddress(Address address){
        validateStreet(address.getStreet());
        validateZipCode(address.getZipCode());
        validateCity(address.getCity());
    }

}
