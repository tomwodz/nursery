package pl.tomwodz.nursery.domain.child;

import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ChildFactoryTest {


    ChildFactory childFactory = new ChildFactory();

    @Test
    void shouldBeCreateNewChildWithChildRequestDto(){

        //given
        ChildRequestDto childRequestDto = new ChildRequestDto("Tomasz", "Wodz", 1L,
                LocalDateTime.now(), 1L);

        //when
        Child child = childFactory.mapFromChildRequestDtoToChild(childRequestDto);

        //then
        assertThat(child.getName()).isEqualTo(childRequestDto.name());
        assertThat(child.getSurname()).isEqualTo(childRequestDto.surname());
        assertThat(child.getGroupChildren().getId()).isEqualTo(childRequestDto.groupChildren_id());
        assertThat(child.getDayBirth()).isEqualTo(childRequestDto.dayBirth());
        assertThat(child.getParent().getId()).isEqualTo(childRequestDto.user_id());

    }

}