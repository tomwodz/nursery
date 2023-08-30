package pl.tomwodz.nursery.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.controllers.rest.InformationRestController;
import pl.tomwodz.nursery.controllers.view.UserViewController;
import pl.tomwodz.nursery.exception.UserNotFoundException;
import pl.tomwodz.nursery.exception.handler.response.ErrorUserResponseDto;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {UserViewController.class,
                                    InformationRestController.class})
@Log4j2
public class UserErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUserResponseDto handleException(UserNotFoundException exception){
        log.warn("UserNotFoundException error while accessing user");
        return new ErrorUserResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
}
