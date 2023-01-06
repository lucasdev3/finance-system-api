package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.entities.Role;
import br.com.lucasdev3.financesystemapi.enumerate.ERole;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.models.ResponseUserModel;
import br.com.lucasdev3.financesystemapi.models.RoleRegistryModel;
import br.com.lucasdev3.financesystemapi.models.UserRegistryModel;
import br.com.lucasdev3.financesystemapi.repositories.RoleRepository;
import br.com.lucasdev3.financesystemapi.services.RoleService;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin")
public class RoleController {
    @Autowired
    RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(RoleController.class);
    @GetMapping(value = "/list/roles")
    public ResponseEntity<Iterable<Role>> getAll(HttpServletRequest request) {
        try {
            LOGGER.info("User controller requested - Find All by: " + request.getRemoteAddr());
            Iterable<Role> iterable = roleService.getAll();
            if (iterable.iterator().hasNext()) return ResponseEntity.ok().body(iterable);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/role/save")
    public ResponseEntity<ResponseModel> save(HttpServletRequest request) {
        LOGGER.info("User controller requested - Save by: " + request.getRemoteAddr());
        return roleService.save();
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
