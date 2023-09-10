package pl.tomwodz.nursery.domain.validator;

import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;

import java.util.LinkedList;
import java.util.List;

class ValidatorInformation {

    List<String> errors;

    List<String> validationInformation(InformationRequestDto informationRequestDto){
        errors = new LinkedList<>();
        validateTitle(informationRequestDto.title());
        validateContent(informationRequestDto.content());
        return errors;
    }

    private void validateTitle(String title) {
        String regex = "^.{3,255}$";
        if (!title.matches(regex)) {
            errors.add("title not validation");
        }
    }
    private void validateContent(String content) {
        String regex = "^.{3,255}$";
        if (!content.matches(regex)) {
            errors.add("content not validation");
        }
    }


}
