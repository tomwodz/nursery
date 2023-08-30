package pl.tomwodz.nursery.validatros;

import pl.tomwodz.nursery.exception.validation.InformationValidationException;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.model.User;

public class InformationValidator {

    public static void validateContent(String name) {
        String regex = "^.{3,255}$";
        if (!name.matches(regex)) {
            throw new InformationValidationException();
        }
    }

    public static void validateTitle(String title) {
        String regex = "^.{3,255}$";
        if (!title.matches(regex)) {
            throw new InformationValidationException();
        }
    }

    public static void validateInformation(Information information){
        validateTitle(information.getTitle());
        validateContent(information.getContent());
    }

}
