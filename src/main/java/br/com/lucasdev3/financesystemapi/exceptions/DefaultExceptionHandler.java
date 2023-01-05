package br.com.lucasdev3.financesystemapi.exceptions;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public void handleConstraintViolationException(ConstraintViolationException ex) {

  }

}
