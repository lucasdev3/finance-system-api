package br.com.lucasdev3.financesystemapi.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.lucasdev3.financesystemapi.entities.Income;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.services.IncomeService;

@RestController
@RequestMapping(value = "/painel/income")
public class IncomeController {

  @Autowired
  IncomeService incomeService;

  private static final Logger LOGGER = Logger.getLogger(IncomeController.class);

  @GetMapping
  public ResponseEntity<Iterable<Income>> getAll(HttpServletRequest request) {
    try {
      LOGGER.info("Income controller requested " + request.getRequestURI() + " - Find All by: "
          + request.getRemoteAddr());
      Iterable<Income> list = incomeService.getAll();
      if (list.iterator().hasNext()) {
        return ResponseEntity.ok().body(list);
      }
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Income> getById(@PathVariable Integer id, HttpServletRequest request) {
    try {
      LOGGER.info("Income controller requested " + request.getRequestURI() + " - Find ID by: "
          + request.getRemoteAddr());
      Income income = incomeService.getById(id);
      if (income != null) {
        return ResponseEntity.ok(income);
      }
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<ResponseModel> save(@RequestBody ExpenseAndIncomeRegistryModel model,
      HttpServletRequest request) {
    LOGGER.info("Income controller requested " + request.getRequestURI() + " - Save by: "
        + request.getRemoteAddr());
    return incomeService.save(model);
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ResponseModel> updateById(@PathVariable Integer id,
      @RequestBody ExpenseAndIncomeRegistryModel model, HttpServletRequest request) {
    LOGGER.info("Income controller requested " + request.getRequestURI() + " - Update by: "
        + request.getRemoteAddr());
    return incomeService.update(id, model);
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<ResponseModel> deleteById(@PathVariable Integer id,
      HttpServletRequest request) {
    LOGGER.info("Income controller requested " + request.getRequestURI() + " - Update by: "
        + request.getRemoteAddr());
    return incomeService.delete(id);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
