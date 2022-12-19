package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.entities.Income;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.services.IncomeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/painel/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    private static final Logger LOGGER = Logger.getLogger(IncomeController.class);

    @GetMapping
    public ResponseEntity<List<Income>> getAll(HttpServletRequest request) {
        try {
            LOGGER.info("Income controller requested - Find All by: " + request.getRemoteAddr());
            List<Income> list = incomeService.getAll();
            if (list.size() > 0) return ResponseEntity.ok().body(list);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Income> getById(@PathVariable Integer id, HttpServletRequest request) {
        try {
            LOGGER.info("Income controller requested - Find ID by: " + request.getRemoteAddr());
            Income income = incomeService.getById(id);
            if (income != null) return ResponseEntity.ok(income);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseModel> save(ExpenseAndIncomeRegistryModel model, HttpServletRequest request) {
        LOGGER.info("Income controller requested - Save by: " + request.getRemoteAddr());
        return incomeService.save(model);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseModel> updateById(@PathVariable Integer id, @RequestBody ExpenseAndIncomeRegistryModel model, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Update by: " + request.getRemoteAddr());
        return incomeService.update(id, model);
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
