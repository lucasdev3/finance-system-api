package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.Expense;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.repositories.ExpenseRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    private static final Logger LOGGER = Logger.getLogger(ExpenseService.class);

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense getById(Integer id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public ResponseEntity<ResponseModel> save(ExpenseAndIncomeRegistryModel model) {
        try {
            expenseRepository.save(new Expense(model));
            return ResponseEntity.ok(new ResponseModel("Despesa cadastrada com sucesso!", model));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao cadastrar despesa!", null));
        }
    }

}
