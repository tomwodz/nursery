package pl.tomwodz.nursery.domain.validator;


import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;

import java.util.List;

@AllArgsConstructor
public class ValidatorFacade {

    private final ValidatorInformation validatorInformation;
    private final ValidatorGroupChildren validatorGroupChildren;
    private final ValidatorChild validatorChild;

    public void validationInformation(InformationRequestDto informationRequestDto){
        List<String> validationResultList = validatorInformation.validationInformation(informationRequestDto);
        if(!validationResultList.isEmpty()){
            throw new ValidationException(validationResultList.toString());
        }
    }

    public void validationGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        List<String> validationResultList = validatorGroupChildren.validationGroupChildren(groupChildrenRequestDto);
        if(!validationResultList.isEmpty()){
            throw new ValidationException(validationResultList.toString());
        }
    }

    public void validationChild(ChildRequestDto childRequestDto){
        List<String> validationResultList = validatorChild.validationChild(childRequestDto);
        if(!validationResultList.isEmpty()){
            throw new ValidationException(validationResultList.toString());
        }
    }
}
