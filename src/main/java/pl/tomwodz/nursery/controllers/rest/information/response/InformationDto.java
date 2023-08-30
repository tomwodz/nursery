package pl.tomwodz.nursery.controllers.rest.information.response;

public record InformationDto(Long id,
                             Long author_id,
                             String title,
                             String content,
                             String dateCreation) {
}
