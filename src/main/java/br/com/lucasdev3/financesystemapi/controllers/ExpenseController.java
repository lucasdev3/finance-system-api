package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.entities.Expense;
import br.com.lucasdev3.financesystemapi.models.ExpenseRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.services.ExpenseService;
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
@RequestMapping(value = "/painel/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    private static final Logger LOGGER = Logger.getLogger(ExpenseController.class);

    @GetMapping
    public ResponseEntity<List<Expense>> getAll(HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Find All by: " + request.getRemoteAddr());
        List<Expense> list = expenseService.getAll();
        if (list.size() > 0) return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Expense> getById(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Find ID by: " + request.getRemoteAddr());
        if (id.matches("^\\d+$")) {
            Expense expense = expenseService.getById(id);
            if (expense != null) return ResponseEntity.ok(expense);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseModel> save(ExpenseRegistryModel model, HttpServletRequest request) {
        LOGGER.info("Expense controller requested - Save by: " + request.getRemoteAddr());
        if (model.getTitle() == null || model.getDescription() == null || model.getExpenseValue() < 0) {
            return ResponseEntity.badRequest().body(new ResponseModel("Dados informados inválidos."));
        }
        return expenseService.save(model);
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
