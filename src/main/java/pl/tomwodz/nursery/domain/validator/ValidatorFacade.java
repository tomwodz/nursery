package pl.tomwodz.nursery.domain.validator;


import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import pl.tomwodz.nursery.domain.address.dto.AddressRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;

import java.util.List;

@AllArgsConstructor
public class ValidatorFacade {

    private final ValidatorInformation validatorInformation;
    private final ValidatorGroupChildren validatorGroupChildren;
    private final ValidatorChild validatorChild;
    private final ValidatorUser validatorUser;
    private final ValidatorAddress validatorAddress;

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

    public void validationUser(UserRequestDto userRequestDto) {
        List<String> validationResultList = validatorUser.validationUser(userRequestDto);
        getAddressToValidationUser(validationResultList, userRequestDto);
        if(!validationResultList.isEmpty()){
            throw new ValidationException(validationResultList.toString());
        }
    }

    public void validationUserToUpdate(UpdateUserRequestDto updateUserRequestDto) {
        List<String> validationResultList = validatorUser.validationUserToUpdate(updateUserRequestDto);
       getAddressToValidationUpdateUser(validationResultList, updateUserRequestDto);
        if(!validationResultList.isEmpty()){
            throw new ValidationException(validationResultList.toString());
        }
    }

    private void getAddressToValidationUser(List<String> validationResultList,
                                            UserRequestDto userRequestDto){
        validatorAddress.validatorAddress(
                        AddressRequestDto.builder()
                                .street(userRequestDto.street())
                                .zipCode(userRequestDto.zipCode())
                                .city(userRequestDto.city())
                                .build())
                .stream()
                .forEach(s -> validationResultList.add(s));
    }

    private void getAddressToValidationUpdateUser(List<String> validationResultList,
                                            UpdateUserRequestDto updateUserRequestDto){
        validatorAddress.validatorAddress(
                        AddressRequestDto.builder()
                                .street(updateUserRequestDto.street())
                                .zipCode(updateUserRequestDto.zipCode())
                                .city(updateUserRequestDto.city())
                                .build())
                .stream()
                .forEach(s -> validationResultList.add(s));
    }

}
