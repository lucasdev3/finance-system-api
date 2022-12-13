package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.ExpenseModel;
import br.com.lucasdev3.financesystemapi.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericFinanceService {
    @Autowired
    ExpenseRepository expenseRepository;
    public List<ExpenseModel> getAllExpensies() {
        return expenseRepository.findAll();
    }

}
