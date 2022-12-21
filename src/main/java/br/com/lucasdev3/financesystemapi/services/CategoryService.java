package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.Category;
import br.com.lucasdev3.financesystemapi.models.CategoryModel;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.repositories.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger LOGGER = Logger.getLogger(CategoryService.class);

    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save(CategoryModel model) {
        try {
            if (!model.getName().isEmpty() && !categoryRepository.existsByName(model.getName())) {
                categoryRepository.save(new Category(model));
                return ResponseEntity.ok(new ResponseModel("Categoria cadastrada com sucesso!", model));
            }
            return ResponseEntity.badRequest().body(new ResponseModel("Nome da categoria inválida ou categoria já existe."));
        } catch (Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao cadastrar categoria!"));
        }
    }

//    public ResponseEntity<ResponseModel> update(Integer id, ExpenseAndIncomeRegistryModel model) {
//        try {
//            Income income = incomeRepository.findById(id).orElse(null);
//            if (income != null) {
//                income.setTitle(model.getTitle());
//                income.setDescription(model.getDescription());
//                income.setIncomeValue(model.getValue());
//                incomeRepository.save(income);
//                return ResponseEntity.ok(new ResponseModel("Receita atualizada com sucesso!", model));
//            }
//            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao atualizar receita"));
//        } catch (Exception e) {
//            LOGGER.error("Message: " + e.getMessage());
//            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao atualizar receita!"));
//        }
//    }
//
//    public ResponseEntity<ResponseModel> delete(Integer id) {
//        try {
//            Income income = incomeRepository.findById(id).orElse(null);
//            if(income == null) {
//                return ResponseEntity.notFound().build();
//            }
//            incomeRepository.deleteById(id);
//            return ResponseEntity.ok(new ResponseModel("Receita deletada com sucesso!"));
//        } catch (Exception e) {
//            LOGGER.error("Message: " + e.getMessage());
//            return ResponseEntity.internalServerError().body(new ResponseModel("Falha ao deletar despesa!"));
//        }
//    }

}
