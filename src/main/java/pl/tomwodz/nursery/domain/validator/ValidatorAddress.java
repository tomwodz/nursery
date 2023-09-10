package pl.tomwodz.nursery.domain.validator;

import pl.tomwodz.nursery.domain.address.dto.AddressRequestDto;
import pl.tomwodz.nursery.infrastructure.validator.error.AddressValidationException;
import pl.tomwodz.nursery.domain.address.Address;

import java.util.LinkedList;
import java.util.List;

class ValidatorAddress {
    List<String> errors;
    public List<String> validatorAddress(AddressRequestDto addressRequestDto){
        errors = new LinkedList<>();
        validateStreet(addressRequestDto.street());
        validateZipCode(addressRequestDto.zipCode());
        validateCity(addressRequestDto.city());
        return errors;
    }
    private void validateStreet(String street) {
        String regex = "^.{3,}$";
        if (!street.matches(regex)) {
            errors.add("street not validation");
        }
    }

    private void validateZipCode(String zipCode) {
        String regex = "[0-9]{2}-[0-9]{3}";
        if (!zipCode.matches(regex)) {
            errors.add("zipCode not validation");
        }
    }

    private void validateCity(String city) {
        String regex = "^.{3,}$";
        if (!city.matches(regex)) {
            errors.add("city not validation");
        }
    }

}
