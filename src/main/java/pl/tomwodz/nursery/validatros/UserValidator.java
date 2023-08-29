package pl.tomwodz.nursery.validatros;

import pl.tomwodz.nursery.exception.validation.UserValidationException;
import pl.tomwodz.nursery.model.User;

public class UserValidator {
    public static void validateName(String name) {
        String regex = "^[A-Z][a-z]+$";
        if (!name.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validateSurname(String surname) {
        String regex = "^[A-Z][a-z]+([ -][A-Z][a-z])?$";
        if (!surname.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validateLogin(String login) {
        String regex = "^.{3,}$";
        if (!login.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regex = "^.{3,}$";
        if (!password.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        String regex = "[0-9]{3}-[0-9]{3}-[0-9]{3}";
        if (!phoneNumber.matches(regex)) {
            throw new UserValidationException();
        }
    }


    public static void validateUser(User user){
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        AddressValidator.validatorAddress(user.getAddress());
    }

    public static void validatePasswordsEquality(String pass1, String pass2){
        if(!pass1.equals(pass2)){
            throw new UserValidationException();
        }
    }

    public static void validateUserToUpdate(User user){
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        AddressValidator.validatorAddress(user.getAddress());
    }

}
