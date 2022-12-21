package br.com.lucasdev3.financesystemapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public void handleConstraintViolationException(ConstraintViolationException ex) {

    }

}
