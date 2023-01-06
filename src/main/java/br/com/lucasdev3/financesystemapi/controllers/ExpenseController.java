package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.entities.Expense;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.services.ExpenseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/painel/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    private static final Logger LOGGER = Logger.getLogger(ExpenseController.class);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @GetMapping
    public ResponseEntity<Iterable<Expense>> getAll(HttpServletRequest request) {
        try {
            LOGGER.info("Expense controller requested - Find All by: " + request.getRemoteAddr());
            var list = expenseService.getAll();
            if (list.iterator().hasNext()) return ResponseEntity.ok().body(list);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Expense> getById(@PathVariable UUID id, HttpServletRequest request) {
        try {
            LOGGER.info("Expense controller requested - Find ID by: " + request.getRemoteAddr());
            Expense expense = expenseService.getById(id);
            if (expense != null) return ResponseEntity.ok(expense);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseModel> save(@RequestBody ExpenseAndIncomeRegistryModel model, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Save by: " + request.getRemoteAddr());
        return expenseService.save(model);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseModel> updateById(@PathVariable UUID id, @RequestBody ExpenseAndIncomeRegistryModel model, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Update by: " + request.getRemoteAddr());
        return expenseService.update(id, model);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable UUID id, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Update by: " + request.getRemoteAddr());
        return expenseService.delete(id);
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
