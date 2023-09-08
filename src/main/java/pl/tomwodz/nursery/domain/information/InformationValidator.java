package pl.tomwodz.nursery.domain.information;

import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationValidationException;

class InformationValidator {

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

    public static void validateInformation(InformationRequestDto informationRequestDto){
        validateTitle(informationRequestDto.title());
        validateContent(informationRequestDto.content());
    }

}
