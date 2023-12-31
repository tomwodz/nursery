package pl.tomwodz.nursery.infrastructure.presence.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.infrastructure.presence.controller.PresenceRestController;
import pl.tomwodz.nursery.infrastructure.presence.controller.PresenceViewController;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {
        PresenceViewController.class,
        PresenceRestController.class
})
@Log4j2
public class PresenceErrorHandler {

    @ExceptionHandler(PresenceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPresenceResponseDto handleException(PresenceNotFoundException exception){
        log.warn("PresenceNotFoundException error while accessing user");
        return new ErrorPresenceResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
}
