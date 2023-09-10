package pl.tomwodz.nursery.domain.validator;

import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;

import java.util.LinkedList;
import java.util.List;

class ValidatorGroupChildren {

    List<String> errors;
    List<String> validationGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        errors = new LinkedList<>();
        validateName(groupChildrenRequestDto.name());
        return errors;
    }

    void validateName(String name) {
        String regex = "^.{3,20}$";
        if (!name.matches(regex)) {
            errors.add("name not validation");
        }
    }


}
