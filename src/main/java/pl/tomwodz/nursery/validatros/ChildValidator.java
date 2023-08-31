package pl.tomwodz.nursery.validatros;

import pl.tomwodz.nursery.exception.validation.ChildValidationException;
import pl.tomwodz.nursery.model.Child;

public class ChildValidator {
    public static void validateName(String name) {
        String regex = "^[A-Z][a-z]+$";
        if (!name.matches(regex)) {
            throw new ChildValidationException();
        }
    }

    public static void validateSurname(String surname) {
        String regex = "^[A-Z][a-z]+([ -][A-Z][a-z])?$";
        if (!surname.matches(regex)) {
            throw new ChildValidationException();
        }
    }

    public static void validateChild(Child child) {
        if (child.getDayOfBirth() == null) {
            throw new ChildValidationException();
        }
        validateName(child.getName());
        validateSurname(child.getSurname());
    }

    public static void validateDayBirth(String dayBrith) {
        String regex = "[2]{1}[0]{1}[2]{1}[0-9]{1}-[0-9]{2}-[0-9]{2}"; //TODO regex to date
        if (!dayBrith.matches(regex)) {
            throw new ChildValidationException();
        }
    }
}
