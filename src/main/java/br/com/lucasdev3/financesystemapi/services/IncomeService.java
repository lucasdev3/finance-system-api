package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.Income;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.repositories.IncomeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IncomeService {
    @Autowired
    IncomeRepository incomeRepository;

    private static final Logger LOGGER = Logger.getLogger(IncomeService.class);

    public List<Income> getAll() {
        return incomeRepository.findAll();
    }

    public Income getById(Integer id) {
        return incomeRepository.findById(id).orElse(null);
    }

    public ResponseEntity<ResponseModel> save(ExpenseAndIncomeRegistryModel model) {
        try {
            incomeRepository.save(new Income(model));
            return ResponseEntity.ok(new ResponseModel("Receita cadastrada com sucesso!", model));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao cadastrar receita!"));
        }
    }

    public ResponseEntity<ResponseModel> update(Integer id, ExpenseAndIncomeRegistryModel model) {
        try {
            Income income = incomeRepository.findById(id).orElse(null);
            if (income != null) {
                income.setTitle(model.getTitle());
                income.setDescription(model.getDescription());
                income.setIncomeValue(model.getValue());
                incomeRepository.save(income);
                return ResponseEntity.ok(new ResponseModel("Receita atualizada com sucesso!", model));
            }
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao atualizar receita"));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao atualizar receita!"));
        }
    }

    public ResponseEntity<ResponseModel> delete(Integer id) {
        try {
            Income income = incomeRepository.findById(id).orElse(null);
            if(income == null) {
                return ResponseEntity.notFound().build();
            }
            incomeRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseModel("Receita deletada com sucesso!"));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao deletar despesa!"));
        }
    }

}
