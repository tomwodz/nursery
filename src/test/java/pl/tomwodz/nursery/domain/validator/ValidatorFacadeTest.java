package pl.tomwodz.nursery.domain.validator;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        LocalDateTime time = LocalDateTime.of(2022,12,03 ,00,00);
        ChildRequestDto childRequestDto = new ChildRequestDto("t","Test",1L,
              time,1L);
        ValidatorFacade validatorFacade = new ValidatorConfiguration().validatorFacade();

        //then
        //when
        assertThrows(ValidationException.class, () -> validatorFacade.validationChild(childRequestDto));

    }

}