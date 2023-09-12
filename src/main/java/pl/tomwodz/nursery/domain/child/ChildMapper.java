package pl.tomwodz.nursery.domain.child;

import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;

class ChildMapper {

    public static ChildResponseDto mapFromChildToChildResponseDto(Child child) {
        return ChildResponseDto.builder()
                .id(child.getId())
                .name(child.getName())
                .surname(child.getSurname())
                .groupChildren_id(child.getGroupChildren().getId())
                .dayBirth(child.getDayBirth())
                .user_id(child.getParent().getId())
                .build();
    }

}
