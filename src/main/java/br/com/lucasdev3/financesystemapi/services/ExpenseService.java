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

import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryService categoryService;

    private static final Logger LOGGER = Logger.getLogger(ExpenseService.class);

    @Transactional(readOnly = true)
    public Iterable<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Expense getById(UUID id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save(ExpenseAndIncomeRegistryModel model) {
        try {
            var category = Optional.of(categoryService.getById(model.getCategoryId())).orElse(null);
            expenseRepository.save(new Expense(model, category));
            return ResponseEntity.ok(new ResponseModel("Despesa cadastrada com sucesso!", model));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao cadastrar despesa!", null));
        }
    }

    @Transactional
    public ResponseEntity<ResponseModel> update(UUID id, ExpenseAndIncomeRegistryModel model) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense != null) {
                expense.setTitle(model.getTitle());
                expense.setDescription(model.getDescription());
                expense.setExpenseValue(model.getValue());
                expenseRepository.save(expense);
                return ResponseEntity.ok(new ResponseModel("Despesa atualizada com sucesso!", model));
            }
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao atualizar despesa"));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao atualizar despesa!", null));
        }
    }

    @Transactional
    public ResponseEntity<ResponseModel> delete(UUID id) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense == null) {
                return ResponseEntity.notFound().build();
            }
            expenseRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseModel("Despesa deletada com sucesso!"));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao deletar despesa!", null));
        }
    }

}
