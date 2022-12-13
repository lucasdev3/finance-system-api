package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.entities.ExpenseModel;
import br.com.lucasdev3.financesystemapi.services.GenericFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ApiController {

    @Autowired
    GenericFinanceService genericService;

    @GetMapping
    public ResponseEntity<String> init() {
        return ResponseEntity.ok().body("API Online");
    }

}
