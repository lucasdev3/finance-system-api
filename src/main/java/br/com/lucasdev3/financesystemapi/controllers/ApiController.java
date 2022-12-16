package br.com.lucasdev3.financesystemapi.controllers;

import br.com.lucasdev3.financesystemapi.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ApiController {

    @Autowired
    ExpenseService genericService;

    @GetMapping
    public ResponseEntity<String> init() {
        return ResponseEntity.ok().body("API Online");
    }

}
