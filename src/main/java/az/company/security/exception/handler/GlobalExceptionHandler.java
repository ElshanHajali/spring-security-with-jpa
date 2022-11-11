package az.company.security.exception.handler;

import az.company.security.exception.UsernameNotFoundExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UsernameNotFoundExceptionResponse usernameNotFoundResponse(UsernameNotFoundExceptionResponse exception) {
        log.error("Exception.usernameNotFoundResponse.error :{} "+exception.getMessage());
        return new UsernameNotFoundExceptionResponse(exception.getMessage(), exception.getCode());
    }

}
