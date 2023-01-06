package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.models.ResponseUserModel;
import br.com.lucasdev3.financesystemapi.models.UserRegistryModel;
import br.com.lucasdev3.financesystemapi.services.UserService;
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

@RestController
@RequestMapping(value = "/auth")
public class UserController {
    @Autowired
    UserService userService;

    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list/users")
    public ResponseEntity<Iterable<ResponseUserModel>> getAll(HttpServletRequest request) {
        try {
            LOGGER.info("User controller requested - Find All by: " + request.getRemoteAddr());
            Iterable<ResponseUserModel> iterable = userService.getAll();
            if (iterable.iterator().hasNext()) return ResponseEntity.ok().body(iterable);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<ResponseModel> save(@RequestBody UserRegistryModel model, HttpServletRequest request) {
        LOGGER.info("User controller requested - Save by: " + request.getRemoteAddr());
        return userService.save(model);
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
