package pl.tomwodz.nursery.controllers.rest.child.response;

public record ChildDto(Long id,
                       String name,
                       String surname,
                       Long groupChildren_id,
                       String dayBirth,
                       Long user_id
                       ) {
}
