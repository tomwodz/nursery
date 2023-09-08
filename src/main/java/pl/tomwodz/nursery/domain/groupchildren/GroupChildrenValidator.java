package pl.tomwodz.nursery.domain.groupchildren;

import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.exception.validation.GroupChildrenValidationException;

class GroupChildrenValidator {

    public static void validateName(String name) {
        String regex = "^.{3,20}$";
        if (!name.matches(regex)) {
            throw new GroupChildrenValidationException();
        }
    }

    public static void validatorGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        validateName(groupChildrenRequestDto.name());
    }
}
