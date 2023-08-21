package pl.tomwodz.nursery.controllers.errors.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.controllers.UserViewController;
import pl.tomwodz.nursery.controllers.errors.UserNotFoundException;
import pl.tomwodz.nursery.controllers.errors.handler.response.ErrorUserResponseDto;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = UserViewController.class)
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
