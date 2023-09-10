package pl.tomwodz.nursery.domain.validator;

import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;

import java.util.LinkedList;
import java.util.List;

class ValidatorUser {

    List<String> errors;

    public List<String> validationUser(UserRequestDto userRequestDto){
        errors = new LinkedList<>();
        validateLogin(userRequestDto.login());
        validatePassword(userRequestDto.password());
        validateName(userRequestDto.name());
        validateSurname(userRequestDto.surname());
        validateEmail(userRequestDto.emial());
        validatePhoneNumber(userRequestDto.phonenumber());
        return errors;
    }

    public List<String> validationPasswordsEquality(String pass1, String pass2){
        errors = new LinkedList<>();
        if(!pass1.equals(pass2)){
            errors.add("password not validation");
        }
        return errors;
    }

    public List<String> validationUserToUpdate(UpdateUserRequestDto updateUserRequestDto) {
        errors = new LinkedList<>();
        validateName(updateUserRequestDto.name());
        validateSurname(updateUserRequestDto.surname());
        validateEmail(updateUserRequestDto.emial());
        validatePhoneNumber(updateUserRequestDto.phonenumber());
        return errors;
    }
   private void validateName(String name) {
        String regex = "^[A-Z][a-z]+$";
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

    private void validateLogin(String login) {
        String regex = "^.{3,}$";
        if (!login.matches(regex)) {
            errors.add("login not validation");
        }
    }

    private void validatePassword(String password) {
        String regex = "^.{3,}$";
        if (!password.matches(regex)) {
            errors.add("password not validation");
        }
    }

    private void validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(regex)) {
            errors.add("e-mial not validation");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        String regex = "[0-9]{3}-[0-9]{3}-[0-9]{3}";
        if (!phoneNumber.matches(regex)) {
            errors.add("phone number not validation");
        }
    }




}
