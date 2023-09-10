package pl.tomwodz.nursery.domain.validator;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorFacadeTest {

    @Test
    void invalidTitleInformationShouldBeThrowException(){

        //given
        InformationRequestDto informationRequestDto = InformationRequestDto.builder()
                .author_id(1L)
                .title("T")
                .content("test")
                .build();
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationInformation(informationRequestDto));

    }

    @Test
    void invalidContentInformationShouldBeThrowException(){

        //given
        InformationRequestDto informationRequestDto = InformationRequestDto.builder()
                .author_id(1L)
                .title("Test")
                .content("t")
                .build();
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationInformation(informationRequestDto));

    }

    @Test
    void invalidNameGroupChildrenShouldBeThrowException(){

        //given
        GroupChildrenRequestDto groupChildrenRequestDto = new GroupChildrenRequestDto("t");
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationGroupChildren(groupChildrenRequestDto));

    }

    @Test
    void invalidNameChildShouldBeThrowException(){

        //given
        ChildRequestDto childRequestDto = new ChildRequestDto("t","Test",1L,"2020-01-02",1L);
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationChild(childRequestDto));

    }

    @Test
    void invalidDayBirthShouldBeThrowException(){

        //given
        ChildRequestDto childRequestDto = new ChildRequestDto("Test","Test",1L,"test",1L);
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationChild(childRequestDto));

    }


}