package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.Category;
import br.com.lucasdev3.financesystemapi.entities.Expense;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.repositories.CategoryRepository;
import br.com.lucasdev3.financesystemapi.repositories.ExpenseRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger LOGGER = Logger.getLogger(ExpenseService.class);

    @Transactional(readOnly = true)
    public Iterable<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Expense getById(Integer id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save(ExpenseAndIncomeRegistryModel model) {
        try {
            var category = categoryRepository.findById(model.getCategoryId()).orElse(null);
            expenseRepository.save(new Expense(model, category));
            return ResponseEntity.ok(new ResponseModel("Despesa cadastrada com sucesso!", model));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao cadastrar despesa!", null));
        }
    }

    @Transactional
    public ResponseEntity<ResponseModel> update(Integer id, ExpenseAndIncomeRegistryModel model) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            //Checando se a categoria existe
            Category category = categoryRepository.findById(model.getCategoryId()).orElse(null);
            if (expense != null && category != null) {
                expense.setTitle(model.getTitle());
                expense.setDescription(model.getDescription());
                expense.setExpenseValue(model.getValue());
                expense.setCategory(category);
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
    public ResponseEntity<ResponseModel> delete(Integer id) {
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
