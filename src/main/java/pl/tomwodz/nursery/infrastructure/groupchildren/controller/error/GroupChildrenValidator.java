package pl.tomwodz.nursery.infrastructure.groupchildren.controller.error;

import pl.tomwodz.nursery.exception.validation.GroupChildrenValidationException;
import pl.tomwodz.nursery.model.GroupChildren;

public class GroupChildrenValidator {

    public static void validateName(String name) {
        String regex = "^.{3,20}$";
        if (!name.matches(regex)) {
            throw new GroupChildrenValidationException();
        }
    }

    public static void validatorGroupChildren(GroupChildren groupChildren){
        validateName(groupChildren.getName());
    }
}
