package br.com.lucasdev3.financesystemapi.repositories;

import br.com.lucasdev3.financesystemapi.entities.Expense;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends GenericRepository<Expense, String> {

}
