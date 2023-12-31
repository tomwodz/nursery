package pl.tomwodz.nursery.domain.validator;

import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.infrastructure.validator.error.ChildValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

class ValidatorChild {

    List<String> errors;
    List<String> validationChild(ChildRequestDto childRequestDto) {
        errors = new LinkedList<>();
        if (childRequestDto.dayBirth() == null) {
            throw new ChildValidationException("Bad day of birth.");
        }
        validateName(childRequestDto.name());
        validateSurname(childRequestDto.surname());
        validateDayBirth(childRequestDto.dayBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return errors;
    }
    private void validateName(String name) {
        String regex = "^.{3,255}$";
        if (!name.matches(regex)) {
            errors.add("name not validation");
        }
    }

    private void validateSurname(String surname) {
        String regex = "^[A-Z][a-z]+([ -][A-Z][a-z])?$";
        if (!surname.matches(regex)) {
            errors.add("surname not validation");
        }
    }

    private void validateDayBirth(String dayBrith) {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        if (!dayBrith.matches(regex)) {
            errors.add("day of birth not validation");
        }
    }

}
